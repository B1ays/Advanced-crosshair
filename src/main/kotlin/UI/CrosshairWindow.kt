package UI

import Data.CSGOEvents
import Data.CrosshairState
import Data.OVERLAY_SIZE
import DataStore.Stores.EnableWithSniperRifleDS
import UI.Icons.CrosshairTypeIcons
import UI.Icons.crosshairtypeicons.CrosshairStandart
import UI.Theme.iconColor
import Utils.setTransparent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.graphics.vector.RenderVectorGroup
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.AwtWindow
import org.koin.compose.koinInject
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
    val mouseEvent by crosshairState.mouseEventsSource.collectAsState()
    val onlyWithSniperRiffle by koinInject<EnableWithSniperRifleDS>().asState()
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
    isEnabled
    color
    strokeWidth
    offset
    lineLength
    type
    playerState

    val density = LocalDensity.current

    val icon = with(CrosshairTypeIcons.CrosshairStandart) {
        rememberVectorPainter(
            defaultWidth = defaultWidth,
            defaultHeight = defaultHeight,
            viewportWidth = viewportWidth,
            viewportHeight = viewportHeight,
            name = name,
            tintColor = iconColor,
            tintBlendMode = tintBlendMode,
            autoMirror = autoMirror,
            content = { _, _ -> RenderVectorGroup(group = root) }
        )
    }

    val overlay = remember {
        Crosshair(crosshairState).apply {
            preferredSize = Dimension(OVERLAY_SIZE, OVERLAY_SIZE)
        }
    }

    AwtWindow(
        visible = true,
        create = {
            JFrame.setDefaultLookAndFeelDecorated(true)
            JFrame("Crosshair").apply {
                preferredSize = Dimension(OVERLAY_SIZE, OVERLAY_SIZE)
                location = position
                isAlwaysOnTop = true
                isUndecorated = true
                iconImage = icon.toAwtImage(
                    density,
                    LayoutDirection.Ltr
                )

                contentPane = overlay
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
                pack()
                background = Color(0, 0, 0, 0)
                setTransparent(rootPane.parent)
            }
        },
        dispose = {
            it.dispose()
        },
        update = {
            overlay.isVisible = if(onlyWithSniperRiffle) {
                playerState.inventory?.activeItem?.type?.get() == Weapon.Type.SNIPER_RIFLE && isEnabled
            } else {
                isEnabled
            }
            with(overlay) {
                paintComponents(graphics)
            }
            it.repaint()
        }
    )
}

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