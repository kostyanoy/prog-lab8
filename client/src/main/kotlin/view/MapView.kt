import javafx.scene.canvas.Canvas
import tornadofx.*

class MapView : View() {
    override val root = borderpane {
        importStylesheet<Styles>()
        addClass(Styles.base)

        center {
            vbox {
                val canvas = Canvas()
                canvas.width = 400.0
                canvas.height = 300.0

                canvas.graphicsContext2D.stroke = c("black")
                canvas.graphicsContext2D.lineWidth = 1.0
                canvas.graphicsContext2D.strokeLine(0.0, canvas.height / 2, canvas.width, canvas.height / 2)
                canvas.graphicsContext2D.strokeLine(canvas.width / 2, 0.0, canvas.width / 2, canvas.height)

                right {
                    hbox {
                        button("Обновить") {}
                    }
                }

                add(canvas)
            }
        }
    }
}
