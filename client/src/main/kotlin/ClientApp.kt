import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import serialize.FrameSerializer
import utils.Interactor
import java.net.ConnectException
import java.net.InetSocketAddress
import java.net.SocketTimeoutException
import java.nio.channels.SocketChannel

/**
 * Class of the application, uses the DI to get parameters
 */
class ClientApp(var serverAddress: String = "localhost", var serverPort: Int = 2228) : KoinComponent {
    private val interactor by inject<Interactor>()
    private val frameSerializer by inject<FrameSerializer>()
    private val logger = KotlinLogging.logger {}

    private var channel: SocketChannel? = null

    /**
     * Connects to the server
     */
    fun start() : Boolean {
        try {
            val c = SocketChannel.open()
            c.socket().connect(InetSocketAddress(serverAddress, serverPort), 5000)
            c.socket()?.soTimeout = 5000 // timeout for server respond

            logger.info { "Произошло подключение к ${c.remoteAddress}" }

            channel = c
        } catch (e: SocketTimeoutException) {
            logger.info { "Сервер не отвечает (${e.message})" }
        } catch (e: ConnectException) {
            logger.info { "Невозможно подключиться (${e.message})" }
        }

        return isOpen()
    }

    fun isOpen() = channel?.isOpen ?: false

    /**
     * Closes the connection
     */
    fun stop() {
        if (channel == null) {
            logger.info { "Попытка закрытия канала. Канала не существует." }
            return
        }
        sendFrame(Frame(FrameType.EXIT))
        channel?.close()
        channel = null
        logger.info { "Канал закрыт" }
    }

    /**
     * Send and receive frame-response
     *
     * @param frame the frame to send
     * @return frame-response
     */
    fun sendAndReceiveFrame(frame: Frame): Frame {
        val t = System.currentTimeMillis()
        sendFrame(frame)
        val response = receiveFrame()
        logger.info { "Запрос занял ${System.currentTimeMillis() - t} мс" }
        return response
    }

    /**
     * Sends frame to the server
     *
     * @param frame which should be sent
     */
    fun sendFrame(frame: Frame) : Boolean {
        if (channel == null) {
            logger.info { "Попытка отправки сообщения на канал. Канала не существует." }
            return false
        }
        val s = frameSerializer.serialize(frame) + "\n"
        channel?.socket()?.getOutputStream()?.write(s.toByteArray())
        logger.info { "Отправлен запрос на сервер ${frame.type}" }
        return true
    }

    /**
     * Receives frame to the server
     *
     * @return [Frame] which server sent
     */
    private fun receiveFrame(): Frame {
        if (channel == null) {
            logger.info { "Попытка принятия сообщения с канала. Канала не существует." }
            return Frame(FrameType.ERROR)
        }
        val array = ArrayList<Byte>()
        var char = channel?.socket()?.getInputStream()?.read()
        var attempts = 0
        val separator = '\n'.code
        while (char != separator && attempts < 10) {
            if (char == -1) {
                attempts++
                logger.info { "Попытка получения ответа №$attempts" }
                Thread.sleep(1000)
                continue
            }
            char?.toByte()?.let { array.add(it) }
            char = channel?.socket()?.getInputStream()?.read()
        }
        val str = String(array.toByteArray())
        val frame = frameSerializer.deserialize(str)
        logger.info { "Получен ответ от сервера ${frame.type}" }
        return frame
    }
}

