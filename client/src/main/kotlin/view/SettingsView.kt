package view

import Styles
import controllers.SettingsController
import javafx.geometry.Pos
import tornadofx.View
import tornadofx.action
import tornadofx.addClass
import tornadofx.borderpane
import tornadofx.button
import tornadofx.center
import tornadofx.hbox
import tornadofx.importStylesheet
import tornadofx.label
import tornadofx.paddingRight
import tornadofx.paddingTop
import tornadofx.top
import tornadofx.vbox

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
                        settingsController.createStringBinding("settings.settingsLbl", this)
                        addClass(Styles.label1)
                    }
                }
            }
        }
        center {
            vbox {
                alignment = Pos.CENTER
                hbox(spacing = 10) {
                    label("Язык:") {
                        settingsController.createStringBinding("settings.languageLbl", this)
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




