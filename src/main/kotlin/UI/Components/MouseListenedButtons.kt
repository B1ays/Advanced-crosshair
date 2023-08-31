@file:OptIn(ExperimentalComposeUiApi::class)

package UI.Components

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CursorListenedIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    activeTint: Color,
    inactiveTint: Color
) {
    var isActive by remember { mutableStateOf(false) }
    val tint by animateColorAsState(if (isActive) activeTint else inactiveTint)
    Icon(
        imageVector,
        contentDescription,
        modifier
            .onPointerEvent(PointerEventType.Enter) { isActive = true }
            .onPointerEvent(PointerEventType.Exit) { isActive = false },
        tint
    )
}