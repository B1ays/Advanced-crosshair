package UI.Icons.crosshairtypeicons

import UI.Icons.CrosshairTypeIcons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val CrosshairTypeIcons.CrosshairTriangle: ImageVector
    get() {
        if (_crosshairtriangle != null) {
            return _crosshairtriangle!!
        }
        _crosshairtriangle = Builder(name = "Crosshairtriangle", defaultWidth = 26.0.dp,
                defaultHeight = 26.0.dp, viewportWidth = 26.0f, viewportHeight = 26.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(13.0672f, 3.6316f)
                lineTo(13.0672f, 13.6316f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(14.9817f, 16.3684f)
                lineTo(25.0183f, 22.3684f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(11.0183f, 16.3684f)
                lineTo(0.9817f, 22.3684f)
            }
        }
        .build()
        return _crosshairtriangle!!
    }

private var _crosshairtriangle: ImageVector? = null
