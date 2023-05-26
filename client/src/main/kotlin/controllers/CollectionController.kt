package controllers

import CommandResult
import data.MusicBand
import org.koin.core.component.inject
import serialize.SerializeManager

class CollectionController : BaseController() {
    private val serializer: SerializeManager by inject<SerializeManager>()
    private var collection: LinkedHashMap<Int, MusicBand> = LinkedHashMap()

    private val clientController: ClientController by inject()

    fun getCollection() = collection

    fun updateCollection(): Boolean {
        error.set("")
        when (val res = clientController.executeCommand("show", arrayOf())) {
            is CommandResult.Success -> {
                logger.info { "Коллекция получена!" }
                collection = serializer.deserialize(res.message!!)
                return true
            }

            is CommandResult.Failure ->
                setErrorAndLog("Ошибка при попытке получить коллекцию: ${res.throwable.message}")

            null -> setErrorAndLog("Не удалось получить коллекцию")
        }
        return false
    }
}