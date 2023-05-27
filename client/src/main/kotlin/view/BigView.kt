package view

import MapView
import Styles
import controllers.AuthController
import controllers.MainController
import controllers.SettingsController
import javafx.geometry.Pos
import tornadofx.*

class BigView : View("Authorization window") {
    private val mainController: MainController by inject()
    private val authController: AuthController by inject()
    private val settingsController: SettingsController by inject()

    private val listView: ListView by inject()
    private val commandsView: CommandsView by inject()
    private val settingsView: SettingsView by inject()
    private val mapView: MapView by inject()

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
                        settingsController.createStringBinding("big.settingsBtn", this)
                        addClass(Styles.settings)
                        action {
                            center = settingsView.root
                        }
                    }
                    button("Выход") {
                        settingsController.createStringBinding("big.exitBtn", this)
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
                        settingsController.createStringBinding("big.listBtn", this)
                        addClass(Styles.button)
                        action {
                            center = listView.root
                        }
                    }

                    button("Карта") {
                        settingsController.createStringBinding("big.mapBtn", this)
                        addClass(Styles.button)
                        action {
                            center = mapView.root
                        }
                    }

                    button("Команды") {
                        settingsController.createStringBinding("big.commandBtn", this)
                        addClass(Styles.button)
                        action {
                            center = commandsView.root
                        }
                    }

                    label("  Имя пользователя:  ") {
                        settingsController.createStringBinding("big.usernameLbl", this)
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
