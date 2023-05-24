package controllers

import ClientApp
import Frame
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tornadofx.*

open class ClientController : Controller(), KoinComponent {
    protected val client: ClientApp by inject<ClientApp>()
    private var token = ""

    protected val logger = KotlinLogging.logger {}

    fun setToken(token: String) {
        this.token = token
    }

    fun sendAndReceiveFrame(frame: Frame): Frame {
        frame.setValue("token", token)
        return client.sendAndReceiveFrame(frame)
    }

    fun sendFrame(frame: Frame) = client.sendFrame(frame)
}