package controllers

import CommandResult
import Frame
import FrameType
import javafx.beans.property.SimpleStringProperty
import org.koin.core.component.inject
import tornadofx.get
import utils.CommandManager

class AuthController : BaseController() {
    val username = SimpleStringProperty()

    private val clientController: ClientController by inject()
    private val settingsController: SettingsController by inject()

    private val commandManager: CommandManager by inject<CommandManager>()
    fun login(login: String, password: String): Boolean = interact("login", login, password)

    fun register(login: String, password: String): Boolean = interact("register", login, password)

    private fun interact(command: String, login: String, password: String): Boolean {
        error.set("")

        if (login.isBlank()) {
            setErrorAndLog(settingsController.messages["authController.blankLoginMsg"])
            return false
        }

        if (password.isBlank()) {
            setErrorAndLog(settingsController.messages["authController.blankPasswordMsg"])
            return false
        }

        username.value = login
        val frame = Frame(FrameType.AUTHORIZE_REQUEST)
        frame.setValue("login", login)
        frame.setValue("password", password)
        frame.setValue("type", command)

        val response = clientController.sendAndReceiveFrame(frame)
        when (response.type) {
            FrameType.AUTHORIZE_RESPONSE -> {
                when (val result = response.body["data"] as CommandResult) {
                    is CommandResult.Failure -> setErrorAndLog("${settingsController.messages["authController.authFailMsg"]}: ${result.throwable.message}")

                    is CommandResult.Success -> {
                        clientController.setToken(result.message as String)
                        commandManager.updateCommands(clientController.sendAndReceiveFrame(Frame(FrameType.LIST_OF_COMMANDS_REQUEST)))
                        return true
                    }
                }
            }

            FrameType.ERROR -> setErrorAndLog("${settingsController.messages["authController.blankPasswordMsg"]}: ${response.body["error"]}")
            else -> setErrorAndLog("${settingsController.messages["authController.blankPasswordMsg"]}: Сервер вернул что-то не то")
        }
        return false
    }
}