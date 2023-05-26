package controllers

import javafx.beans.property.SimpleStringProperty
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import tornadofx.*

open class BaseController : Controller(), KoinComponent {
    val error = SimpleStringProperty()
    protected val logger = KotlinLogging.logger {}

    fun setErrorAndLog(text: String) {
        logger.error { text }
        error.set(text)
    }
}