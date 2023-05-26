package controllers

import ClientApp
import Frame
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.CommandManager

open class ClientController : BaseController(), KoinComponent {
    private val commandManager: CommandManager by inject<CommandManager>()
    private var token = ""

    val client: ClientApp by inject<ClientApp>()

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