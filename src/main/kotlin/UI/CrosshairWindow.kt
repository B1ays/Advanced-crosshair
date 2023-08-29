package UI

import Data.CrosshairState
import Data.OVERLAY_SIZE
import Utils.setTransparent
import androidx.compose.runtime.*
import java.awt.Color
import java.awt.Dimension
import java.awt.Point
import javax.swing.JFrame
import javax.swing.WindowConstants

@Suppress("UNUSED_EXPRESSION")
@Composable
fun CrosshairWindow(crosshairState: CrosshairState, position: Point) {
    val isEnabled by crosshairState.isShowed.collectAsState()
    val color by crosshairState.color.collectAsState()
    val strokeWidth by crosshairState.strokeWidth.collectAsState()
    val offset by crosshairState.offsetFromCenter.collectAsState()
    val lineLength by crosshairState.lineLength.collectAsState()
    val type by crosshairState.type.collectAsState()
    // For recomposition work
    isEnabled
    color
    strokeWidth
    offset
    lineLength
    type

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
    LaunchedEffect(isEnabled) {
        jFrame.isVisible = isEnabled
    }
}