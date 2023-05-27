package controllers

import org.koin.core.component.KoinComponent
import tornadofx.get

class MainController : BaseController(), KoinComponent {
    private val clientController: ClientController by inject()
    private val settingsController: SettingsController by inject()

    fun connect(): Boolean {
        error.set("")
        val res = clientController.client.start()
        if (!res) {
            setErrorAndLog(settingsController.messages["mainController.connectionFailMsg"])
        }
        return res
    }

    fun disconnect() = clientController.client.stop()
}