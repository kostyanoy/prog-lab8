package view

import Styles
import controllers.MainController
import javafx.geometry.Pos
import tornadofx.*

class MainView : View("Connection window") {
    private val mainController: MainController by inject()

    override val root = vbox {
        importStylesheet<Styles>()
        addClass(Styles.base)

        label(mainController.error) {
            addClass(Styles.error)
        }
        label("Добро пожаловать! Готовы исследовать наше чудесное приложение?") {
            addClass(Styles.label1)
        }
        button("Конечно!") {
            action {
                if (mainController.connect()) {
                    replaceWith<AuthView>()
                }
            }
        }
        button("В следующий раз(") {
            action {
                currentStage?.close()
            }
        }
        style {
            spacing = 20.px
            alignment = Pos.CENTER
        }
    }
}

