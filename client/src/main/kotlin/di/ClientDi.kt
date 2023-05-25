package di

import ClientApp
import FileManager
import Frame
import data.MusicBand
import org.koin.dsl.module
import serialize.FrameSerializer
import serialize.SerializeManager
import serialize.Serializer
import utils.*
import utils.console.ConsoleManager
import utils.state.AuthState

/**
 * Koin module for the client part
 */
val clientModule = module {
    single<ReaderWriter> {
        ConsoleManager()
    }

    single<Serializer<LinkedHashMap<Int, MusicBand>>> {
        SerializeManager()
    }

    single<Serializer<Frame>> {
        FrameSerializer()
    }

    single<Validator> {
        ValidationManager(interactor = get(), userManager = get())
    }

    single {
        CommandManager()
    }

    single {
        FileManager()
    }

    single {
        FrameSerializer()
    }

    single<Interactor> {
        InteractionManager(userManager = get(), fileManager = get(), commandManager = get(), AuthState())
    }

    single {
        ClientApp()
    }
}