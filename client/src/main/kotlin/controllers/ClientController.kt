package controllers

import ClientApp
import Frame
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tornadofx.*
import utils.CommandManager

open class ClientController : Controller(), KoinComponent {
    private val commandManager: CommandManager by inject<CommandManager>()
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

    fun executeCommand(command: String, args: Array<Any>) = commandManager.executeCommand(client, token, command, args)
}