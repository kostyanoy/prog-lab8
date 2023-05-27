package view

import Styles
import controllers.AuthController
import controllers.MainController
import controllers.SettingsController
import javafx.geometry.Pos
import tornadofx.*

class AuthView : View("Authorization window") {
    private val mainController: MainController by inject()
    private val authController: AuthController by inject()
    private val settingsController: SettingsController by inject()

    override val root = borderpane {
        importStylesheet<Styles>()
        addClass(Styles.base)

        top {
            hbox {
                alignment = Pos.TOP_RIGHT
                paddingRight = 10

                button("Назад") {
                    settingsController.createStringBinding("auth.returnLbl", this)
                    addClass(Styles.exit)
                    action {
                        mainController.disconnect()
                        replaceWith<MainView>()
                    }
                }
            }
        }
        center {
            vbox {
                importStylesheet<Styles>()
                label(authController.error) {
                    addClass(Styles.error)
                }
                label("Логин:") {
                    settingsController.createStringBinding("auth.loginLbl", this)
                    addClass(Styles.label2)
                }
                val login = textfield {
                    addClass(Styles.textField)
                }

                label("Пароль:") {
                    settingsController.createStringBinding("auth.passwordLbl", this)
                    addClass(Styles.label2)
                }
                val password = passwordfield {
                    addClass(Styles.textField)
                }
                button("Войти") {
                    settingsController.createStringBinding("auth.loginBtn", this)
                    addClass(Styles.button)
                    action {
                        if (authController.login(login.text, password.text)) {
                            replaceWith<BigView>()
                        }
                    }
                }
                button("Зарегистрироваться") {
                    settingsController.createStringBinding("auth.registerBtn", this)
                    action {
                        if (authController.register(login.text, password.text)) {
                            replaceWith<BigView>()
                        }
                    }
                }
                style {
                    spacing = 10.px
                    alignment = Pos.CENTER
                }
            }
        }
    }
}