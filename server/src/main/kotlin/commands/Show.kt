package commands

import ArgumentType
import CommandResult
import data.MusicBand
import org.koin.core.component.inject
import serialize.Serializer

/**
 * The command outputs all the elements of the collection in a string representation to the standard output stream
 */
class Show : StorageCommand() {
    private val serializer : Serializer<LinkedHashMap<Int, MusicBand>> by inject()

    override fun getDescription(): String =
        "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении"

    override fun execute(args: Array<Any>): CommandResult {
        val collection = storage.getCollection { true }
        val message = serializer.serialize(collection)
        return CommandResult.Success("Show", message)
    }

    override fun getArgumentTypes(): Array<ArgumentType> = arrayOf()
}
