package controllers

import CommandResult
import Frame
import FrameType

class AuthController : ClientController() {
    private val bigController: BigController by inject()

    fun login(login: String, password: String) : Boolean {
        bigController.username.value = login
        return interact("login", login, password)
    }

    fun register(login: String, password: String) : Boolean = interact("register", login, password)

    private fun interact(command: String, login: String, password: String): Boolean {
        val frame = Frame(FrameType.AUTHORIZE_REQUEST)
        frame.setValue("login", login)
        frame.setValue("password", password)
        frame.setValue("type", command)

        val response = sendAndReceiveFrame(frame)
        when (response.type) {
            FrameType.AUTHORIZE_RESPONSE -> {
                when (val result = response.body["data"] as CommandResult) {
                    is CommandResult.Failure -> {
                        logger.info { "Авторизация не пройдена: ${result.throwable.message}" }
                    }
                    is CommandResult.Success -> {
                        setToken(result.message as String)
                        return true
                    }
                }
            }

            FrameType.ERROR -> logger.info { "Авторизация не пройдена: ${response.body["error"]}" }
            else -> logger.info { "Авторизация не пройдена: Сервер вернул что-то не то" }
        }
        return false
    }
}