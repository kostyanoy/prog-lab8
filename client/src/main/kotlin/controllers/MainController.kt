package controllers

import org.koin.core.component.KoinComponent

class MainController : KoinComponent, ClientController() {
    fun connect(): Boolean = client.start()

    fun disconnect() = client.stop()
}