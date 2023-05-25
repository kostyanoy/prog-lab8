package controllers

import javafx.beans.property.StringProperty
import tornadofx.Controller

class BigController : Controller() {
    val username: StringProperty
        get() {
            TODO("здесь должно быть имя пользователя")
        }

    fun exit() {
        TODO("реализация отключения клиента")
    }
}