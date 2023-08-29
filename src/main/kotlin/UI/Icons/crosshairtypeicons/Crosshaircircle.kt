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

public val CrosshairTypeIcons.CrosshairCircle: ImageVector
    get() {
        if (_crosshaircircle != null) {
            return _crosshaircircle!!
        }
        _crosshaircircle = Builder(name = "Crosshaircircle", defaultWidth = 26.0.dp, defaultHeight =
                26.0.dp, viewportWidth = 26.0f, viewportHeight = 26.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(13.0f, 13.0f)
                moveToRelative(-6.5f, 0.0f)
                arcToRelative(6.5f, 6.5f, 0.0f, true, true, 13.0f, 0.0f)
                arcToRelative(6.5f, 6.5f, 0.0f, true, true, -13.0f, 0.0f)
            }
        }
        .build()
        return _crosshaircircle!!
    }

private var _crosshaircircle: ImageVector? = null
