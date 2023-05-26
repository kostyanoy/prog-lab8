package controllers

import CommandResult
import data.MusicBand
import org.koin.core.component.inject
import serialize.SerializeManager

class CollectionController : ClientController() {
    private val serializer: SerializeManager by inject<SerializeManager>()
    private var collection: LinkedHashMap<Int, MusicBand> = LinkedHashMap()

    fun getCollection() = collection

    fun updateCollection(): Boolean {
        when (val res = executeCommand("show", arrayOf())) {
            is CommandResult.Success -> {
                logger.info { "Коллекция получена!" }
                collection = serializer.deserialize(res.message!!)
                return true
            }

            is CommandResult.Failure -> {
                logger.info { "Ошибка при попытке получить коллекцию: ${res.throwable.message}" }
                return false
            }

            null -> {
                logger.info { "Не удалось получить коллекцию" }
                return false
            }
        }
    }
}