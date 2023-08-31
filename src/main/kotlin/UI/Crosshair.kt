package UI

import Data.CrosshairState
import Data.CrosshairType
import Data.OVERLAY_SIZE
import java.awt.*
import javax.swing.JPanel

class Crosshair(
    private val crosshairState: CrosshairState
): JPanel() {

    private val color: Color get() = crosshairState.color.value
    private val strokeWidth: Float get() = crosshairState.strokeWidth.value
    private val offset: Float get() = crosshairState.offsetFromCenter.value
    private val lineLength: Float get() = crosshairState.lineLength.value
    private val type: CrosshairType get() = crosshairState.type.value

    public override fun paintComponent(g: Graphics) {
        val w = OVERLAY_SIZE
        val h = OVERLAY_SIZE
        val g2 = g.create() as Graphics2D
        g2.color = color
        g2.stroke = BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER)
        val widthCenter = w/2
        val heightCenter = h/2

        with(type) {
            g2.draw(
                Point(widthCenter, heightCenter),
                offset.toInt(),
                lineLength.toInt()
            )
        }
    }
}