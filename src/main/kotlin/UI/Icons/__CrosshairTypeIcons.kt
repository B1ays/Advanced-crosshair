package UI.Icons

import UI.Icons.crosshairtypeicons.CrosshairCircle
import UI.Icons.crosshairtypeicons.CrosshairStandart
import UI.Icons.crosshairtypeicons.CrosshairTriangle
import UI.Icons.crosshairtypeicons.CrosshairTShaped
import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.collections.List as ____KtList

public object CrosshairTypeIcons

private var __CrosshairTypeIcons: ____KtList<ImageVector>? = null

public val CrosshairTypeIcons.CrosshairTypeIcons: ____KtList<ImageVector>
  get() {
    if (__CrosshairTypeIcons != null) {
      return __CrosshairTypeIcons!!
    }
    __CrosshairTypeIcons= listOf(CrosshairCircle, CrosshairStandart, CrosshairTriangle,
        CrosshairTShaped)
    return __CrosshairTypeIcons!!
  }
