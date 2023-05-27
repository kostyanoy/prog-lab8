
import javafx.stage.Stage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tornadofx.App
import tornadofx.launch
import view.MainView

class MyApp(): App(MainView::class), KoinComponent{
    override fun start(stage: Stage) {
        stage.width = 1100.0
        stage.height = 800.0
        super.start(stage)
    }
    fun main(address: String, port: Int) {
        launch<MyApp>()
        val client: ClientApp by inject<ClientApp>()
        client.serverAddress = address
        client.serverPort = port
    }
}