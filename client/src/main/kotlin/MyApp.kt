
import javafx.stage.Stage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tornadofx.App
import tornadofx.launch

class MyApp(): App(MainView::class), KoinComponent{
    override fun start(stage: Stage) {
        stage.width = 1000.0
        stage.height = 700.0
        super.start(stage)
    }
    fun main(address: String, port: Int) {
        launch<MyApp>()
        val client: ClientApp by inject<ClientApp>()
        client.serverAddress = address
        client.serverPort = port
    }
}