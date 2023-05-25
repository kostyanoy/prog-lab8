package controllers

import data.MusicBand
import org.koin.core.component.inject
import serialize.Serializer

class CollectionController : ClientController() {
    private val serializer: Serializer<LinkedHashMap<Int, MusicBand>> by inject<Serializer<LinkedHashMap<Int, MusicBand>>>()
    var collection: LinkedHashMap<Int, MusicBand> = LinkedHashMap()

    fun updateCollection(): Boolean {
        when (val res = executeCommand("show", arrayOf())) {
            is CommandResult.Success -> {
                logger.info { "Коллекция получена!" }
                collection =  serializer.deserialize(res.message!!)
                return true
            }
            is CommandResult.Failure -> {
                logger.info { "Ошибка при попытке получить коллекцию: ${res.throwable.message}" }
                return false
            }
            null -> {
                logger.info { "Не удалось получить коллекцию" }
                return false
            }
        }
    }
}