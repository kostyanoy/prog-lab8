
import javafx.stage.Stage
import tornadofx.App
import tornadofx.launch

class MyApp: App(MainView::class){
    override fun start(stage: Stage) {
        stage.width = 1000.0
        stage.height = 700.0
        super.start(stage)
    }
    fun main(args: Array<String>) {
        launch<MyApp>(args)
    }
}