
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val label2 by cssclass()
        val label1 by cssclass()
        val button by cssclass()
        val textField by cssclass()
        val base by cssclass()
        val back by cssclass()
    }

    init {
        base{
            backgroundColor += c("#F5EFE4")
            padding = box(20.px)
        }
        label1 {
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            textFill = c("#4F4A45")
        }
        label2 {
            textFill = c("#4F4A45")
            fontWeight = FontWeight.BOLD
            fontSize = 18.px
        }

        button {
            prefWidth = 200.px
            padding = box(10.px, 20.px)
            fontSize = 16.px
            backgroundColor += c("#6C5F5B")
            textFill = c("#CDAB81")
        }

        textField {
            prefWidth = 200.px
            maxWidth = 300.px
            padding = box(10.px)
            borderRadius += box(5.px)
            backgroundColor += c("#EAE2D6")
            textFill = c("#867666")
            fontSize = 16.px
        }
        back{
            fontSize = 16.px
            padding = box(5.px, 10.px)
            textFill = c("#CDAB81")
            prefWidth = 80.px
        }
    }
}
