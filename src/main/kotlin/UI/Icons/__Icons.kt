package UI.Icons

import UI.Icons.icons.CounterStrike
import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.collections.List as ____KtList

public object Icons

private var __Icons: ____KtList<ImageVector>? = null

public val Icons.Icons: ____KtList<ImageVector>
  get() {
    if (__Icons != null) {
      return __Icons!!
    }
    __Icons= listOf(CounterStrike)
    return __Icons!!
  }
