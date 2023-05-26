package controllers

import CommandResult
import Frame
import FrameType
import javafx.beans.property.SimpleStringProperty
import org.koin.core.component.inject
import utils.CommandManager

class AuthController : BaseController() {
    val username = SimpleStringProperty()

    private val clientController: ClientController by inject()
    private val commandManager: CommandManager by inject<CommandManager>()
    fun login(login: String, password: String): Boolean = interact("login", login, password)

    fun register(login: String, password: String): Boolean = interact("register", login, password)

    private fun interact(command: String, login: String, password: String): Boolean {
        error.set("")

        if (login.isBlank()) {
            setErrorAndLog("Введите логин")
            return false
        }

        if (password.isBlank()) {
            setErrorAndLog("Введите пароль")
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
                    is CommandResult.Failure -> setErrorAndLog("Авторизация не пройдена: ${result.throwable.message}")

                    is CommandResult.Success -> {
                        clientController.setToken(result.message as String)
                        val commands =
                            commandManager.updateCommands(clientController.sendAndReceiveFrame(Frame(FrameType.LIST_OF_COMMANDS_REQUEST)))
                        return true
                    }
                }
            }

            FrameType.ERROR -> setErrorAndLog("Авторизация не пройдена: ${response.body["error"]}")
            else -> setErrorAndLog("Авторизация не пройдена: Сервер вернул что-то не то")
        }
        return false
    }
}