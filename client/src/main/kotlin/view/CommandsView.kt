package  view

import Styles
import controllers.CommandsController
import controllers.SettingsController
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
    private val settingsController: SettingsController by inject()

    private val commandManager: CommandManager by inject<CommandManager>()

    private var current: String? = null

    init {
        primaryStage.width = 1000.0
        primaryStage.height = 800.0
    }

    fun setUpdate() {
        root.center = allView.root
        current = "update"
    }

    override val root = borderpane {
        importStylesheet<Styles>()
        addClass(Styles.base)

        top {
            left {
                vbox(spacing = 5) {
                    label(commandsController.error) //style this
                    label(commandsController.output) //style this
                    label("Команды") {
                        settingsController.createStringBinding("commands.commandsLbl", this)
                        addClass(Styles.label2)
                    }
                    button("Clear") {
                        addClass(Styles.button)
                        action {
                            center = emptyView.root
                            current = "clear"
                            commandsController.clearFields()
                        }
                    }
                    button("CountGreaterThanDescription") {
                        addClass(Styles.button)
                        action {
                            center = descriptionView.root
                            current = "count_greater_than_description"
                            commandsController.clearFields()
                        }
                    }
                    button("FilterLessThanGenre") {
                        addClass(Styles.button)
                        action {
                            center = filterView.root
                            current = "filter_less_than_genre"
                            commandsController.clearFields()
                        }
                    }
                    button("Insert") {
                        addClass(Styles.button)
                        action {
                            center = allView.root
                            current = "insert"
                            commandsController.clearFields()
                        }
                    }
                    button("RemoveGreater") {
                        addClass(Styles.button)
                        action {
                            center = removeGreaterView.root
                            current = "remove_greater"
                            commandsController.clearFields()
                        }
                    }
                    button("RemoveGreaterKey") {
                        addClass(Styles.button)
                        action {
                            center = keyView.root
                            current = "remove_greater_key"
                            commandsController.clearFields()
                        }
                    }
                    button("RemoveKey") {
                        addClass(Styles.button)
                        action {
                            center = keyView.root
                            current = "remove_key"
                            commandsController.clearFields()
                        }
                    }
                    button("ReplaceIfLowe") {
                        addClass(Styles.button)
                        action {
                            center = allView.root
                            current = "replace_if_lowe"
                            commandsController.clearFields()
                        }
                    }
                    button("Update") {
                        addClass(Styles.button)
                        action {
                            center = allView.root
                            current = "update"
                            commandsController.clearFields()
                        }
                    }
                }
            }

            bottom {
                vbox {
                    alignment = Pos.CENTER

                    button("Отправить") {
                        settingsController.createStringBinding("commands.sendBtn", this)
                        addClass(Styles.button)
                        action {
                            if (current != null){
                                commandsController.sendCommand(current!!, commandManager.getArgs(current!!))
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class CommandView : View() {
    protected val commandsController: CommandsController by inject()
    protected val settingsController: SettingsController by inject()

    class EmptyView : CommandView() {
        override val root = vbox {
            importStylesheet<Styles>()
            addClass(Styles.base)

            hbox {
                label("Вы уверены?") {
                    settingsController.createStringBinding("commands.confirmLbl", this)
                    addClass(Styles.label2)
                }
            }
        }
    }

    class FilterView : CommandView() {
        override val root = vbox {
            importStylesheet<Styles>()
            addClass(Styles.base)

            hbox {
                label("Введите жанр (PROGRESSIVE_ROCK, HIP_HOP, PSYCHEDELIC_CLOUD_RAP, SOUL, POST_PUNK): ") {
                    settingsController.createStringBinding("commands.genreLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.genre)
                }
            }
        }
    }

    class KeyView : CommandView() {
        override val root = vbox {
            importStylesheet<Styles>()
            addClass(Styles.base)

            hbox {
                label("Вы должны ввести аргумент типа число:") {
                    settingsController.createStringBinding("commands.keyLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.key)
                }
            }
        }
    }

    class RemoveGreaterView : CommandView() {
        override val root = vbox(spacing = 5) {
            importStylesheet<Styles>()
            addClass(Styles.base)
            hbox {
                label("Введите название банды:") {
                    settingsController.createStringBinding("commands.bandNameLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.name)
                }
            }

            hbox {
                label("Введите координату Х (<=552):") {
                    settingsController.createStringBinding("commands.xLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.x)
                }
            }

            hbox {
                label("Введите координату Y:") {
                    settingsController.createStringBinding("commands.yLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.y)
                }
            }

            hbox {
                label("Введите количество участников:") {
                    settingsController.createStringBinding("commands.participantsLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.participants)
                }
            }

            hbox {
                label("Введите количество альбомов (может быть пустым):") {
                    settingsController.createStringBinding("commands.albumsLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.albums)
                }
            }

            hbox {
                label("Введите описание банды:") {
                    settingsController.createStringBinding("commands.descriptionLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.description)
                }
            }

            hbox {
                label("Введите жанр (PROGRESSIVE_ROCK, HIP_HOP, PSYCHEDELIC_CLOUD_RAP, SOUL, POST_PUNK): ") {
                    settingsController.createStringBinding("commands.genreLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.genre)
                }
            }

            hbox {
                label("Введите название лучшего альбома (может быть пустым):") {
                    settingsController.createStringBinding("commands.bestAlbumNameLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.bestAlbumName)
                }
            }

            hbox {
                label("Введите длительность альбома:") {
                    settingsController.createStringBinding("commands.bestAlbumLengthLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.bestAlbumLength)
                }
            }
        }
    }

    class DescriptionView : CommandView() {
        override val root = vbox {
            importStylesheet<Styles>()
            addClass(Styles.base)

            hbox {
                label("Вы должны ввести аргумент типа строка:") {
                    settingsController.createStringBinding("commands.descriptionLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.description)
                }
            }
        }
    }

    class AllView : CommandView() {
        override val root = vbox(spacing = 5) {
            importStylesheet<Styles>()
            addClass(Styles.base)

            hbox {
                label("Вы должны ввести аргумент типа число:") {
                    settingsController.createStringBinding("commands.keyLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.key)
                }
            }

            hbox {
                label("Введите название банды:") {
                    settingsController.createStringBinding("commands.bandNameLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.name)
                }
            }

            hbox {
                label("Введите координату Х (<=552):") {
                    settingsController.createStringBinding("commands.xLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.x)
                }
            }

            hbox {
                label("Введите координату Y:") {
                    settingsController.createStringBinding("commands.yLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.y)
                }
            }

            hbox {
                label("Введите количество участников:") {
                    settingsController.createStringBinding("commands.participantsLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.participants)
                }
            }

            hbox {
                label("Введите количество альбомов (может быть пустым):") {
                    settingsController.createStringBinding("commands.albumsLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.albums)
                }
            }

            hbox {
                label("Введите описание банды:") {
                    settingsController.createStringBinding("commands.descriptionLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.description)
                }
            }

            hbox {
                label("Введите жанр (PROGRESSIVE_ROCK, HIP_HOP, PSYCHEDELIC_CLOUD_RAP, SOUL, POST_PUNK): ") {
                    settingsController.createStringBinding("commands.genreLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.genre)
                }
            }

            hbox {
                label("Введите название лучшего альбома (может быть пустым):") {
                    settingsController.createStringBinding("commands.bestAlbumNameLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.bestAlbumName)
                }
            }

            hbox {
                label("Введите длительность альбома:") {
                    settingsController.createStringBinding("commands.bestAlbumLengthLbl", this)
                    addClass(Styles.label2)
                }
                textfield() {
                    textProperty().bindBidirectional(commandsController.bestAlbumLength)
                }
            }
        }
    }
}


