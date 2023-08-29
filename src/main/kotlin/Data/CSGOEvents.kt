package Data

import kotlinx.coroutines.flow.MutableStateFlow
import uk.oczadly.karl.csgsi.GSIListener
import uk.oczadly.karl.csgsi.state.BombState
import uk.oczadly.karl.csgsi.state.GameState
import uk.oczadly.karl.csgsi.state.PlayerState
import uk.oczadly.karl.csgsi.state.RoundState

class CSGOEvents {
    val playerState = MutableStateFlow(PlayerState())
    val bombState = MutableStateFlow(BombState())
    val roundState = MutableStateFlow(RoundState())

    var listener = GSIListener { state: GameState, _ ->
        state.player.ifPresent {
            playerState.value = it
        }
        state.bomb.ifPresent {
            bombState.value = it
        }
        state.round.ifPresent {
            roundState.value = it
        }
    }

}