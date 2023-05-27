package view

import Styles
import controllers.MainController
import controllers.SettingsController
import javafx.geometry.Pos
import tornadofx.*

class MainView : View("Connection window") {
    private val mainController: MainController by inject()
    private val settingsController: SettingsController by inject()

    override val root = vbox {
        importStylesheet<Styles>()
        addClass(Styles.base)

        label(mainController.error) {
            addClass(Styles.error)
        }
        label("Добро пожаловать! Готовы исследовать наше чудесное приложение?") {
            settingsController.createStringBinding("main.welcomeLbl", this)
            addClass(Styles.label1)
        }
        button("Конечно!") {
            settingsController.createStringBinding("main.yesBtn", this)
            addClass(Styles.button)
            action {
                if (mainController.connect()) {
                    replaceWith<AuthView>()
                }
            }
        }
        button("В следующий раз(") {
            settingsController.createStringBinding("main.noBtn", this)
            addClass(Styles.button)
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

