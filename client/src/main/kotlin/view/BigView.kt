package view

import Styles
import controllers.AuthController
import controllers.MainController
import javafx.geometry.Pos
import tornadofx.*

class BigView : View("Authorization window") {
    private val mainController: MainController by inject()
    private val authController: AuthController by inject()

    private val listView: ListView by inject()
    private val commandsView: CommandsView by inject()
    private val settingsView: SettingsView by inject()


    init {
        primaryStage.width = 1000.0
        primaryStage.height = 700.0
    }

    override val root = borderpane {
        importStylesheet<Styles>()
        addClass(Styles.base)

        top {
            vbox {
                hbox {
                    spacing = 5.0
                    alignment = Pos.TOP_RIGHT
                    paddingRight = 5
                    paddingTop = 5.0

                    button("Настройки") {
                        addClass(Styles.settings)
                        action {
                            center = settingsView.root
                        }
                    }
                    button("Выход") {
                        addClass(Styles.exit)
                        action {
                            mainController.disconnect()
                            replaceWith<MainView>()
                        }
                    }
                }


                hbox {
                    spacing = 10.0
                    paddingRight = 20
                    paddingLeft = 20
                    paddingTop = 20.0

                    button("Список") {
                        addClass(Styles.button)
                        action {
                            center = listView.root
                        }
                    }

                    button("Карта") {
                        addClass(Styles.button)
                        action {

                        }
                    }

                    button("Команды") {
                        addClass(Styles.button)
                        action {
                            center = commandsView.root
                        }
                    }

                    label("  Имя пользователя  ") {
                        addClass(Styles.label2)
                    }
                    label(authController.username) {
                        addClass(Styles.label2)
                    }
                }
            }
        }
    }
}
