package UI.Icons

import UI.Icons.icons.Cross
import UI.Icons.icons.Palette
import UI.Icons.icons.`Palette-empty`
import UI.Icons.icons.Power
import UI.Icons.icons.SettingsSliders
import UI.Icons.icons.WindowMinimize
import UI.Icons.icons.WindowRestore
import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.collections.List as ____KtList

object Icons

private var __Icons: ____KtList<ImageVector>? = null

val Icons.Icons: ____KtList<ImageVector>
  get() {
    if (__Icons != null) {
      return __Icons!!
    }
    __Icons= listOf(Cross, `Palette-empty`, Palette, Power, SettingsSliders, WindowMinimize,
        WindowRestore)
    return __Icons!!
  }
