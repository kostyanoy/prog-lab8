package view

import Styles
import controllers.CollectionController
import data.MusicBand
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import tornadofx.*

class ListView : View() {
    private val collectionController: CollectionController by inject()
    private val musicBands = FXCollections.observableArrayList<MusicBand>()

    override val root = borderpane {
        importStylesheet<Styles>()
        addClass(Styles.base)

        label(collectionController.error) {
            addClass(Styles.error)
        }

        center = tableview(musicBands) {
            column("ID", MusicBand::idProperty)
            column("Name", MusicBand::nameProperty)
            column("Coordinates", MusicBand::coordinatesProperty)
            column("Participants", MusicBand::numberOfParticipantsProperty)
            column("Albums Count", MusicBand::albumsCountProperty)
            column("Description", MusicBand::descriptionProperty)
            column("Genre", MusicBand::genreProperty)
            column("Best Album", MusicBand::bestAlbumProperty)
            column("Creation Time", MusicBand::creationTimeProperty)
            column("Owner", MusicBand::ownerProperty)
        }

        left = button("Обновить таблицу") {
            action {
                collectionController.updateCollection()
                updateTable()
            }
        }
    }

    private fun updateTable() {
        musicBands.clear()
        collectionController.getCollection().values.forEach { musicBands.add(it as MusicBand?) }
    }
}

fun MusicBand.nameProperty() = SimpleStringProperty(name)
fun MusicBand.coordinatesProperty() = SimpleObjectProperty(coordinates)
fun MusicBand.numberOfParticipantsProperty() = SimpleIntegerProperty(numberOfParticipants)
fun MusicBand.albumsCountProperty() = SimpleObjectProperty(albumsCount)
fun MusicBand.descriptionProperty() = SimpleStringProperty(description)
fun MusicBand.genreProperty() = SimpleObjectProperty(genre)
fun MusicBand.bestAlbumProperty() = SimpleObjectProperty(bestAlbum)
fun MusicBand.idProperty() = SimpleIntegerProperty(id)
fun MusicBand.creationTimeProperty() = SimpleObjectProperty(creationTime)
fun MusicBand.ownerProperty() = SimpleStringProperty(owner)