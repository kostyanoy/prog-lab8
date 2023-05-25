import controllers.AuthController
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
import tornadofx.passwordfield
import tornadofx.px
import tornadofx.style
import tornadofx.textfield
import tornadofx.top
import tornadofx.vbox

class AuthView : View("Authorization window") {
    //    private val mainController: MainController by inject()
    private val authController: AuthController by inject()

    override val root = borderpane {
        importStylesheet<Styles>()
        addClass(Styles.base)
        top {
            hbox {
                alignment = Pos.TOP_RIGHT
                paddingRight = 10

                button("Назад") {
                    addClass(Styles.exit)
                    action {
//                        mainController.disconnect()
                        replaceWith<MainView>()
                    }
                }
            }
        }
        center {
            vbox {
                label("Логин:") {
                    addClass(Styles.label2)
                }
                textfield {
                    addClass(Styles.textField)
                }

                label("Пароль:") {
                    addClass(Styles.label2)
                }
                passwordfield {
                    addClass(Styles.textField)
                }
                button("Войти") {
                    addClass(Styles.button)
                    action {
                        authController.login("123", "123")
                        replaceWith<BigView>()

                    }
                }
                button("Зарегистрироваться") {
                    addClass(Styles.button)
                    action {
                        // authController.register()
                        replaceWith<BigView>()
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