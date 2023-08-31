package UI

import Data.CSGOEvents
import Data.CrosshairState
import Data.OVERLAY_SIZE
import Utils.setTransparent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.awt.ComposePanel
import uk.oczadly.karl.csgsi.state.components.Weapon
import java.awt.Color
import java.awt.Dimension
import java.awt.Point
import javax.swing.JFrame
import javax.swing.WindowConstants

@Suppress("UNUSED_EXPRESSION")
@Composable
fun CrosshairWindow(
    crosshairState: CrosshairState,
    csgoEvents: CSGOEvents,
    position: Point
) {
    //Crosshair parameters
    val onlyWithSniperRiffle by crosshairState.onlyWithSniperRiffle.collectAsState()
    val moveOnShooting by crosshairState.moveOnShooting.collectAsState()
    val isEnabled by crosshairState.isShowed.collectAsState()
    val color by crosshairState.color.collectAsState()
    val strokeWidth by crosshairState.strokeWidth.collectAsState()
    val offset by crosshairState.offsetFromCenter.collectAsState()
    val lineLength by crosshairState.lineLength.collectAsState()
    val type by crosshairState.type.collectAsState()

    // CS:GO Integration State
    val playerState by csgoEvents.playerState.collectAsState()
    /*val bombState by csgoEvents.bombState.collectAsState()
    val roundState by csgoEvents.roundState.collectAsState()*/

    // For recomposition work
    onlyWithSniperRiffle
    moveOnShooting
    isEnabled
    color
    strokeWidth
    offset
    lineLength
    type
    playerState

    val overlay = remember {
        Crosshair(crosshairState).apply {
            preferredSize = Dimension(OVERLAY_SIZE, OVERLAY_SIZE)
        }
    }

    val jFrame = remember {
        JFrame.setDefaultLookAndFeelDecorated(true)
        JFrame("Crosshair").apply {
            preferredSize = Dimension(OVERLAY_SIZE, OVERLAY_SIZE)
            location = position
            isAlwaysOnTop = true
            isUndecorated = true

            contentPane = overlay
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
            pack()
            isVisible = true
            background = Color(0, 0, 0, 0)
            setTransparent(rootPane.parent)
        }
    }
    SideEffect {
        with(overlay) {
            paintComponents(graphics)
        }
        jFrame.repaint()

    }
    LaunchedEffect(isEnabled, onlyWithSniperRiffle, playerState) {
        println("Change visibility settings")
        jFrame.isVisible = if(onlyWithSniperRiffle) {
            playerState.inventory?.activeItem?.type?.get() == Weapon.Type.SNIPER_RIFLE && isEnabled
        } else {
            isEnabled
        }
    }
}

@Suppress("UNUSED_EXPRESSION")
@Composable
fun StatisticWindow(csgoEvents: CSGOEvents) {
    val playerState by csgoEvents.playerState.collectAsState()
    val bombState by csgoEvents.bombState.collectAsState()
    val roundState by csgoEvents.roundState.collectAsState()

    val overlay = remember {
        ComposePanel().apply {
            preferredSize = Dimension(OVERLAY_SIZE, OVERLAY_SIZE)
            setContent {
                Column {
                    Text("Player name: ${playerState.name}")
                    Text("Current weapon: ${playerState.inventory?.activeItem?.weapon?.get()?.name}")
                    Text("Current weapon is Sniper: ${playerState.inventory?.activeItem?.type?.get() == Weapon.Type.SNIPER_RIFLE}")
                    Text("Round phase: ${roundState.phase?.get()}")
                    /*Text("Bomb timer: ${bombState.countdown}")*/
                }
            }
        }
    }

    val jFrame = remember {
        JFrame.setDefaultLookAndFeelDecorated(true)
        JFrame("Crosshair").apply {
            preferredSize = Dimension(300, 200)
            isAlwaysOnTop = true
            isUndecorated = true

            contentPane = overlay
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
            pack()
            isVisible = true
            background = Color(0, 0, 0, 0)
            setTransparent(rootPane.parent)
        }
    }
    SideEffect {
        with(overlay) {
            paintComponents(graphics)
        }
        jFrame.repaint()

    }
}