
import controllers.AuthController
import controllers.MainController
import javafx.geometry.Pos
import tornadofx.*
class AuthView : View("Authorization window") {
//    private val mainController: MainController by inject()
    private val authController: AuthController by inject()

    override val root = borderpane  {
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
                        // authController.login()
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