import di.clientModule
import mu.KotlinLogging
import org.koin.core.context.GlobalContext.startKoin


/**
 * Main function that starts the application
 */
fun main(args: Array<String>) {
    startKoin {
        modules(clientModule)
    }

    val logger = KotlinLogging.logger {}

    var serverPort = 2228
    if (args.isNotEmpty()) {
        serverPort = args[0].toIntOrNull() ?: serverPort
    }

    logger.info { "Выбран порт: $serverPort" }
    MyApp().main("localhost", 2228)
}

