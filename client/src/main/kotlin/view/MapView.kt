
import controllers.CollectionController
import data.MusicBand
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import javafx.util.Duration
import tornadofx.*
import view.CommandsView
import java.util.*

class MapView : View() {
    private val collectionController: CollectionController by inject()
    private var collection = collectionController.getCollection().values
    private var selectedBand: MusicBand? = null
    private val colorMap: MutableMap<String, Color> = mutableMapOf()

    override val root = borderpane {
        importStylesheet<Styles>()
        addClass(Styles.base)

        center {
            vbox {
                val canvas = Canvas()
                canvas.width = 500.0
                canvas.height = 400.0
                canvas.graphicsContext2D.stroke = Color.BLACK
                canvas.graphicsContext2D.lineWidth = 1.0
                canvas.graphicsContext2D.strokeLine(0.0, canvas.height / 2, canvas.width, canvas.height / 2)
                canvas.graphicsContext2D.strokeLine(canvas.width / 2, 0.0, canvas.width / 2, canvas.height)
                add(canvas)
            }
        }
        right {
            vbox {
                spacing = 10.0
                paddingAll = 10.0
                rectangle {
                }
            }
        }
        bottom {
            hbox {
                spacing = 5.0
                paddingAll = 5.0
                button("Обновить") {
                    action {
                        collectionController.updateCollection()
                        collection = collectionController.getCollection().values
                        updateLabels()
                    }
                }
                button("Изменить"){
                    action {
                        replaceWith<CommandsView>()
                    }

                }
            }
        }
    }

    init {
        updateLabels()
    }
    private fun updateLabels() {
        val canvas = Canvas()
        canvas.width = 500.0
        canvas.height = 400.0

        val graphics= canvas.graphicsContext2D
        graphics.stroke = Color.BLACK
        graphics.lineWidth = 1.0
        graphics.strokeLine(0.0, canvas.height / 2, canvas.width, canvas.height / 2)
        graphics.strokeLine(canvas.width / 2, 0.0, canvas.width / 2, canvas.height)

        var index = 0
        val time = Timeline()

        for (musicBand in collection) {
            val x = musicBand.coordinates.x
            val y = musicBand.coordinates.y

            val X = canvas.width / 2 + x
            val Y = canvas.height / 2 - y

            val numberOfParticipants = musicBand.numberOfParticipants
            val size = if (numberOfParticipants > 15) 15.0 else numberOfParticipants.toDouble()
            val halfSize = size / 2

            val owner = musicBand.owner
            val color = if (owner != null) getColor(owner) else Color.BLACK

            val keyFrame = KeyFrame(Duration.seconds(index.toDouble() * 0.1), {
                graphics.fill = color
                graphics.fillOval(X - halfSize, Y - halfSize, size, size)
            })
            time.keyFrames.add(keyFrame)
            canvas.setOnMouseClicked { event ->
                val clickX = event.x - canvas.width / 2
                val clickY = canvas.height / 2 - event.y
                for (band in collection) {
                    val bandX = band.coordinates.x
                    val bandY = band.coordinates.y
                    if (fault(clickX, clickY, bandX, bandY)) {
                        selectedBand = band
                        updatePanel()
                        return@setOnMouseClicked
                    }
                }
                selectedBand = null
                updatePanel()
            }
            index++
        }
        time.play()
        root.center.replaceChildren(canvas)
    }

    private fun updatePanel() {
        root.right.replaceChildren {
            vbox {
                spacing = 10.0
                paddingAll = 10.0
                if (selectedBand != null) {
                    vbox {
                        spacing = 5.0
                        label {
                            addClass(Styles.label2)
                            text = "Выбрана группа:"
                            style {
                                fontWeight = FontWeight.BOLD
                            }
                        }
                        textarea {
                            isEditable = false
                            text = """
                    |Name: ${selectedBand!!.name}
                    |Coordinates: ${selectedBand!!.coordinates}
                    |Number of Participants: ${selectedBand!!.numberOfParticipants}
                    |Albums Count: ${selectedBand!!.albumsCount}
                    |Description: ${selectedBand!!.description}
                    |Genre: ${selectedBand!!.genre}
                    |Best Album: ${selectedBand!!.bestAlbum}
                    |ID: ${selectedBand!!.id}
                    |Creation Time: ${selectedBand!!.creationTime}
                    |Owner: ${selectedBand!!.owner}
                """.trimMargin()
                            prefWidth = 500.0
                            prefHeight = 400.0
                            style {
                                fontSize = 18.px
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getColor(owner: String): Color {
        return colorMap.getOrPut(owner) { generateColor() }
    }
    private fun generateColor(): Color {
        val random = Random()
        val hue = random.nextDouble() * 360
        val saturation = 0.5 + random.nextDouble() * 0.5
        val brightness = 0.7 + random.nextDouble() * 0.3
        return Color.hsb(hue, saturation, brightness)
    }
    private fun fault(x1: Double, y1: Double, x2: Float, y2: Double): Boolean {
        val tolerance = 5.0
        val dx = x1 - x2
        val dy = y1 - y2
        val distance = Math.sqrt(dx * dx + dy * dy)
        return distance <= tolerance
    }
}
