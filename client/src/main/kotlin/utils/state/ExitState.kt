package utils.state

/**
 * State for stopping the client
 */
class ExitState : InteractionState() {
    override fun start() {
        interactor.getClient().stop()
        stop()
    }
}