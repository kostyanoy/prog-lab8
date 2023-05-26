package controllers

import org.koin.core.component.KoinComponent

class MainController : BaseController(), KoinComponent {
    private val clientController: ClientController by inject()

    fun connect(): Boolean {
        error.set("")
        val res = clientController.client.start()
        if (!res) {
            setErrorAndLog("Не удалось установить соединение с сервером")
        }
        return res
    }

    fun disconnect() = clientController.client.stop()
}