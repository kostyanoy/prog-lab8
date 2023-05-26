package  view
import Styles
import javafx.geometry.Pos
import tornadofx.*

class CommandsView : View("Settings window") {
    private val allView: AllView by inject()
    private val keyView: KeyView by inject()
    private val filterView: FilterView by inject()
    private val removeGreaterView: RemoveGreaterView by inject()
    private val descriptionView: DescriptionView by inject()

    init {
        primaryStage.width = 1000.0
        primaryStage.height = 800.0
    }

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
                        addClass(Styles.button)
                        action {
                        }
                    }
                    button("CountGreaterThanDescription") {
                        addClass(Styles.button)
                        action {
                            center = descriptionView.root
                        }
                    }
                    button("FilterLessThanGenre") {
                        addClass(Styles.button)
                        action {
                            center = filterView.root
                        }
                    }
                    button("Insert") {
                        addClass(Styles.button)
                        action {
                            center = allView.root
                        }
                    }
                    button("RemoveGreater") {
                        addClass(Styles.button)
                        action {
                            center = removeGreaterView.root
                        }
                    }
                    button("RemoveGreaterKey") {
                        addClass(Styles.button)
                        action {
                            center = keyView.root
                        }
                    }
                    button("RemoveKey") {
                        addClass(Styles.button)
                        action {
                            center = keyView.root
                        }
                    }
                    button("ReplaceIfLowe") {
                        addClass(Styles.button)
                        action {
                            center = allView.root
                        }
                    }
                    button("Update") {
                        addClass(Styles.button)
                        action {
                            center = allView.root
                        }
                    }
                }
            }

            bottom {
                vbox {
                    alignment = Pos.CENTER

                    button("Отправить") {
                        addClass(Styles.button)
                        action {
                        }
                    }
                }
            }
        }
    }
}

class FilterView : View() {
    override val root = vbox {
        importStylesheet<Styles>()
        addClass(Styles.base)

        hbox {
            label("Введите жанр (PROGRESSIVE_ROCK, HIP_HOP, PSYCHEDELIC_CLOUD_RAP, SOUL, POST_PUNK): ") {
                addClass(Styles.label2)
            }
            textfield()
        }
    }
}

class KeyView : View() {
    override val root = vbox {
        importStylesheet<Styles>()
        addClass(Styles.base)

        hbox {
            label("Вы должны ввести аргумент типа число:") {
                addClass(Styles.label2)
            }
            textfield()
        }
    }
}

class RemoveGreaterView : View() {
    override val root = vbox(spacing = 5) {
        importStylesheet<Styles>()
        addClass(Styles.base)
        hbox {
            label("Введите название банды:") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите координату Х (<=552):") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите координату Y:") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите количество участников:") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите количество альбомов (может быть пустым):") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите описание банды:") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите жанр (PROGRESSIVE_ROCK, HIP_HOP, PSYCHEDELIC_CLOUD_RAP, SOUL, POST_PUNK): ") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите название лучшего альбома (может быть пустым):") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите длительность альбома:") {
                addClass(Styles.label2)
            }
            textfield()
        }
    }
}

class DescriptionView : View() {
    override val root = vbox {
        importStylesheet<Styles>()
        addClass(Styles.base)

        hbox {
            label("Вы должны ввести аргумент типа строка:") {
                addClass(Styles.label2)
            }
            textfield()
        }
    }
}

class AllView : View() {
    override val root = vbox(spacing = 5) {
        importStylesheet<Styles>()
        addClass(Styles.base)

        hbox {
            label("Вы должны ввести аргумент типа число:") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите название банды:") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите координату Х (<=552):") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите координату Y:") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите количество участников:") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите количество альбомов (может быть пустым):") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите описание банды:") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите жанр (PROGRESSIVE_ROCK, HIP_HOP, PSYCHEDELIC_CLOUD_RAP, SOUL, POST_PUNK): ") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите название лучшего альбома (может быть пустым):") {
                addClass(Styles.label2)
            }
            textfield()
        }

        hbox {
            label("Введите длительность альбома:") {
                addClass(Styles.label2)
            }
            textfield()
        }
    }
}