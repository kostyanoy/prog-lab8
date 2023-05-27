package view

import Styles
import controllers.SettingsController
import javafx.geometry.Pos
import tornadofx.*

class SettingsView : View("Settings window") {
    private val settingsController: SettingsController by inject()

    override val root = borderpane {
        importStylesheet<Styles>()
        addClass(Styles.base)

        top {
            vbox {
                hbox {
                    spacing = 5.0
                    alignment = Pos.CENTER
                    paddingRight = 5
                    paddingTop = 5.0

                    label("Настройки") {
                           addClass(Styles.label1)
                    }

                    //!_________________________________________________________!
                    //ПРИМЕР ИСПОЛЬЗОВАНИЯ ЛОКАЛИЗАЦИИ
                    val l = label(){
                        settingsController.createStringBinding("example", this)
                    }

                    button(messages["example1"]) {
                        settingsController.createStringBinding("example1", this)
                    }
                    //|''''''''''''''''''''''''''''''''''''''''''''''''''''''''''|
                }
            }
        }
        center {
            vbox {
                alignment = Pos.CENTER
                hbox(spacing = 10) {
                    label("Язык:") {
                        addClass(Styles.label2)
                    }
                    button("English") {
                        addClass(Styles.button)
                        action {
                            settingsController.change("en")
                        }
                    }
                    button("Русский") {
                        addClass(Styles.button)
                        action {
                            settingsController.change("ru")
                        }
                    }
                }
            }
        }
    }
}




