package controllers

import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.Labeled
import org.jetbrains.annotations.PropertyKey
import tornadofx.*
import java.util.*

class SettingsController : BaseController() {
    companion object {
        val currentLocaleProperty = SimpleObjectProperty(Locale.getDefault())
    }

    init {
        // Привязываем текущий язык к ResourceBundle messages
        messages = ResourceBundle.getBundle("messages", currentLocaleProperty.value)
        // При изменении текущего языка обновляем ResourceBundle messages
        currentLocaleProperty.addListener { _, _, newLocale ->
            messages = ResourceBundle.getBundle("messages", newLocale)
        }
    }

    fun getMessage(@PropertyKey(resourceBundle = "messages") key: String) = messages[key]

    fun createStringBinding(@PropertyKey(resourceBundle = "messages") key: String, labeled: Labeled) {
        labeled.text = getMessage(key)
        currentLocaleProperty.addListener { _, _, _ ->
            labeled.text = getMessage(key)
        }
    }

    fun change(lang: String) {
        val locale = if (lang == "ru") Locale.getDefault() else Locale(lang)
        currentLocaleProperty.set(locale)
    }

}
