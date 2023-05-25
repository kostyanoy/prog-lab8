package view

import BigView
import Styles
import controllers.SettingsController
import javafx.geometry.Pos
import tornadofx.*

class SettingsView : View("Settings window") {
    private val settingsController: SettingsController by inject()

    init {
        primaryStage.width = 600.0
        primaryStage.height = 200.0
    }

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

                    hbox {
                        alignment = Pos.TOP_RIGHT
                        button("Назад") {
                            addClass(Styles.exit)
                            action {
                                replaceWith<BigView>()
                            }
                        }
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
//                                settingsController.change()
                            }
                        }
                        button("Русский") {
                            addClass(Styles.button)
                            action {
//                                settingsController.change()
                            }
                        }
                    }
                }
            }
        }
    }
}




