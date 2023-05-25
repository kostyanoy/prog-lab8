package controllers

import javafx.beans.property.SimpleObjectProperty
import tornadofx.*
import java.util.*

class SettingsController : Controller() {
    private val currentLocaleProperty = SimpleObjectProperty(Locale.getDefault())

    init {
        // Привязываем текущий язык к ResourceBundle messages
        messages = ResourceBundle.getBundle("messages", currentLocaleProperty.value)
        // При изменении текущего языка обновляем ResourceBundle messages
        currentLocaleProperty.addListener { _, _, newLocale ->
            messages = ResourceBundle.getBundle("messages", newLocale)
        }
    }

    fun change(lang: String) {
        currentLocaleProperty.set(Locale(lang))
    }
}