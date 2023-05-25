package controllers

import javafx.beans.property.SimpleStringProperty
import tornadofx.Controller

class BigController : Controller() {
    val username = SimpleStringProperty()
}