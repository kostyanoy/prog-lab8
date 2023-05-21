package view

import tornadofx.*

class MainView : View("My View") {
    override val root = vbox{
        label("SAS")
        button("Click")
    }
}
