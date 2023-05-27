package  view

import ArgumentType
import Styles
import controllers.CommandsController
import javafx.geometry.Pos
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tornadofx.*
import utils.CommandManager

class CommandsView : View("Settings window"), KoinComponent {
    private val allView: CommandView.AllView by inject()
    private val keyView: CommandView.KeyView by inject()
    private val filterView: CommandView.FilterView by inject()
    private val removeGreaterView: CommandView.RemoveGreaterView by inject()
    private val descriptionView: CommandView.DescriptionView by inject()
    private val emptyView: CommandView.EmptyView by inject()

    private val commandsController: CommandsController by inject()
    private val commandManager: CommandManager by inject<CommandManager>()

    private var current: String? = null

    override val root = borderpane {
        importStylesheet<Styles>()
        addClass(Styles.base)

        top {
            left {
                vbox(spacing = 5) {
                    label("Команды") {
                        addClass(Styles.label2)
                    }
                    button("Clear") {
                        action {
                            center = emptyView.root
                            current = "clear"
                            commandsController.clearFields()
                            commandsController.error.value = ""
                            commandsController.output.value = ""
                        }
                    }
                    button("CountGreaterThanDescription") {
                        action {
                            center = descriptionView.root
                            current = "count_greater_than_description"
                            commandsController.clearFields()
                            commandsController.error.value = ""
                            commandsController.output.value = ""
                        }
                    }
                    button("FilterLessThanGenre") {
                        action {
                            center = filterView.root
                            current = "filter_less_than_genre"
                            commandsController.clearFields()
                            commandsController.error.value = ""
                            commandsController.output.value = ""
                        }
                    }
                    button("Insert") {
                        action {
                            center = allView.root
                            current = "insert"
                            commandsController.clearFields()
                            commandsController.error.value = ""
                            commandsController.output.value = ""
                        }
                    }
                    button("RemoveGreater") {
                        action {
                            center = removeGreaterView.root
                            current = "remove_greater"
                            commandsController.clearFields()
                            commandsController.error.value = ""
                            commandsController.output.value = ""
                        }
                    }
                    button("RemoveGreaterKey") {
                        action {
                            center = keyView.root
                            current = "remove_greater_key"
                            commandsController.clearFields()
                            commandsController.error.value = ""
                            commandsController.output.value = ""
                        }
                    }
                    button("RemoveKey") {
                        action {
                            center = keyView.root
                            current = "remove_key"
                            commandsController.clearFields()
                            commandsController.error.value = ""
                            commandsController.output.value = ""
                        }
                    }
                    button("ReplaceIfLowe") {
                        action {
                            center = allView.root
                            current = "replace_if_lowe"
                            commandsController.clearFields()
                            commandsController.error.value = ""
                            commandsController.output.value = ""
                        }
                    }
                    button("Update") {
                        action {
                            center = allView.root
                            current = "update"
                            commandsController.clearFields()
                            commandsController.error.value = ""
                            commandsController.output.value = ""
                        }
                    }
                }
            }
            bottom {
                vbox {
                    alignment = Pos.CENTER
                    label("Смотреть сюда") {
                    addClass(Styles.label2)}

                    label(commandsController.error) {
                        addClass(Styles.error)
                    }

                    label(commandsController.output) {
                        addClass(Styles.label1)
                    }
                    button("Отправить") {
                        action {
                            if (current != null) {
                                commandsController.sendCommand(current!!, commandManager.getArgs(current!!))
                            }
                        }
                    }
                    vboxConstraints {
                        marginTop = 10.0
                    }
                }
            }

        }
    }
}

sealed class CommandView(val argumentTypes: Array<ArgumentType>) : View() {
    protected val commandsController: CommandsController by inject()

    class EmptyView : CommandView(arrayOf()) {
        override val root = vbox {
            importStylesheet<Styles>()
            addClass(Styles.base)

            hbox {
                alignment = Pos.CENTER
                label("Вы уверены?") {
                    addClass(Styles.label2)
                }
            }
        }
    }

    class FilterView : CommandView(arrayOf(ArgumentType.GENRE)) {
        override val root = vbox {
            importStylesheet<Styles>()
            addClass(Styles.base)

            hbox {
                label("Введите жанр (PROGRESSIVE_ROCK, HIP_HOP, PSYCHEDELIC_CLOUD_RAP, SOUL, POST_PUNK): ") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.genre)
                }
            }
        }
    }

    class KeyView : CommandView(arrayOf(ArgumentType.INT)) {
        override val root = vbox {
            importStylesheet<Styles>()
            addClass(Styles.base)
            hbox {
                label("Вы должны ввести аргумент типа число:") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.key)
                }
            }
        }
    }

    class RemoveGreaterView : CommandView(arrayOf(ArgumentType.MUSIC_BAND)) {
        override val root = vbox(spacing = 5) {
            importStylesheet<Styles>()
            addClass(Styles.base)
            hbox {
                label("Введите название банды:") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.name)
                }
            }

            hbox {
                label("Введите координату Х (<=552):") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.x)
                }
            }

            hbox {
                label("Введите координату Y:") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.y)
                }
            }

            hbox {
                label("Введите количество участников:") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.participants)
                }
            }

            hbox {
                label("Введите количество альбомов (может быть пустым):") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.albums)
                }
            }

            hbox {
                label("Введите описание банды:") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.description)
                }
            }

            hbox {
                label("Введите жанр (PROGRESSIVE_ROCK, HIP_HOP, PSYCHEDELIC_CLOUD_RAP, SOUL, POST_PUNK): ") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.genre)
                }
            }

            hbox {
                label("Введите название лучшего альбома (может быть пустым):") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.bestAlbumName)
                }
            }

            hbox {
                label("Введите длительность альбома:") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.bestAlbumLength)
                }
            }
        }
    }

    class DescriptionView : CommandView(arrayOf(ArgumentType.STRING)) {
        override val root = vbox {
            importStylesheet<Styles>()
            addClass(Styles.base)
            hbox {
                label("Вы должны ввести аргумент типа строка:") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.description)
                }
            }
        }
    }

    class AllView : CommandView(arrayOf(ArgumentType.INT, ArgumentType.MUSIC_BAND)) {
        override val root = vbox(spacing = 5) {
            importStylesheet<Styles>()
            addClass(Styles.base)

            hbox {
                label("Вы должны ввести аргумент типа число:") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.key)
                }
            }

            hbox {
                label("Введите название банды:") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.name)
                }
            }

            hbox {
                label("Введите координату Х (<=552):") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.x)
                }
            }

            hbox {
                label("Введите координату Y:") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.y)
                }
            }

            hbox {
                label("Введите количество участников:") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.participants)
                }
            }

            hbox {
                label("Введите количество альбомов (может быть пустым):") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.albums)
                }
            }

            hbox {
                label("Введите описание банды:") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.description)
                }
            }

            hbox {
                label("Введите жанр (PROGRESSIVE_ROCK, HIP_HOP, PSYCHEDELIC_CLOUD_RAP, SOUL, POST_PUNK): ") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.genre)
                }
            }

            hbox {
                label("Введите название лучшего альбома (может быть пустым):") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.bestAlbumName)
                }
            }

            hbox {
                label("Введите длительность альбома:") {
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.bestAlbumLength)
                }
            }
        }
    }
}


