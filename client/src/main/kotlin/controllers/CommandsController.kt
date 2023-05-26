package controllers

import ArgumentType
import CommandResult
import data.Album
import data.Coordinates
import data.MusicBand
import data.MusicGenre
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class CommandsController : BaseController() {
    private val clientController: ClientController by inject()

    val output = SimpleStringProperty()

    val key = stringProperty()
    val name = stringProperty()
    val x = stringProperty()
    val y = stringProperty()
    val participants = stringProperty()
    val albums = stringProperty()
    val description = stringProperty()
    val genre = stringProperty()
    val bestAlbumName = stringProperty()
    val bestAlbumLength = stringProperty()

    fun sendCommand(command: String, argumentTypes: Array<ArgumentType>) : Boolean {
        output.value = ""
        error.value = ""
        val commandArgs = arrayListOf<Any>()
        for (arg in argumentTypes) {
            val a = when (arg) {
                ArgumentType.INT -> checkInt()
                ArgumentType.STRING -> checkString()
                ArgumentType.GENRE -> checkGenre()
                ArgumentType.MUSIC_BAND -> checkMusicBand()
            } as Any?
            if (a == null) {
                setErrorAndLog("Заполните правильно все необходимые поля")
                return false
            }
            commandArgs.add(a)
        }

        when (val res = clientController.executeCommand(command, commandArgs.toArray())){
            is CommandResult.Success -> {
                output.value = res.message ?: "Команда выполнена"
                return true
            }
            is CommandResult.Failure -> {
                res.throwable.message?.let { setErrorAndLog(it) }
            }
            null -> {
                setErrorAndLog("Сервер вернул непотребщину")
            }
        }
        return false
    }

    fun clearFields() {
        key.value = null
        name.value = null
        x.value = null
        y.value = null
        participants.value = null
        albums.value = null
        description.value = null
        genre.value = null
        bestAlbumName.value = null
        bestAlbumLength.value = null
    }

    private fun checkInt(): Int? {
        if (key.value == null) return null
        return key.value.toIntOrNull()
    }

    private fun checkString(): String? {
        return description.value
    }

    private fun checkGenre(): MusicGenre? {
        if (genre.value == null) return null
        return MusicGenre.valueOfOrNull(genre.value.uppercase())
    }

    private fun checkMusicBand(): MusicBand? {
        if (name.value == null || x.value == null || y.value == null || participants.value == null || description.value == null || genre.value == null) {
            return null
        }

        if (name.value.isBlank() || x.value.toFloatOrNull() == null || y.value.toDoubleOrNull() == null || participants.value.toIntOrNull() == null ||
            MusicGenre.valueOfOrNull(genre.value.uppercase()) == null) {
            return null
        }

        if (albums.value != null && albums.value.toLongOrNull() != null && albums.value.toLong() < 0)
            return null

        if (x.value.toFloat() > 552 || participants.value.toInt() <= 0) {
            return null
        }

        var album: Album? = null
        if (bestAlbumName.value != null && bestAlbumName.value.isNotBlank()) {
            if (bestAlbumLength.value == null || bestAlbumLength.value.toLongOrNull() == null || bestAlbumLength.value.toLong() < 0) return null
            album = Album(bestAlbumName.value, bestAlbumLength.value.toLong())
        }

        return MusicBand(
            name = name.value,
            coordinates = Coordinates(x.value.toFloat(), y.value.toDouble()),
            numberOfParticipants = participants.value.toInt(),
            albumsCount = if (albums.value == null) null else albums.value.toLongOrNull(),
            description = description.value,
            genre = MusicGenre.valueOf(genre.value.uppercase()),
            bestAlbum = album
        )
    }
}