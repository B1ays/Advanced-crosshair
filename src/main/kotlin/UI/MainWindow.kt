package UI

import Data.CrosshairState
import UI.Components.CursorListenedIcon
import UI.Icons.CrosshairTypeIcons
import UI.Icons.Icons
import UI.Icons.crosshairtypeicons.CrosshairStandart
import UI.Icons.icons.*
import UI.Screens.CSGOIntegrationScreen
import UI.Screens.ColorSettingsScreen
import UI.Screens.MainScreen
import UI.Theme.*
import UI.Utils.minimize
import UI.Utils.navigateTo
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.RenderVectorGroup
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import org.koin.compose.koinInject

@Composable
fun MainWindow(
    onClose: () -> Unit
) {

    val crosshairState: CrosshairState = koinInject()
    val isCrosshairEnabled by crosshairState.isShowed.collectAsState()
    var navigator: Navigator? by mutableStateOf(null)

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

    Window(
        onCloseRequest = onClose,
        title = "Settings",
        transparent = true,
        undecorated = true,
        resizable = false,
        icon = icon /**/,
        state = WindowState(
            isMinimized = false,
            width = 500.dp,
            height = 350.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(windowShapeLarge)
                .border(windowBorderStroke, windowShapeLarge),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.15F)
                    .background(backgroundColorSemiTransparent),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                IconToggleButton(
                        isCrosshairEnabled,
                        onCheckedChange = {
                        with(crosshairState) {
                            if (it) show() else hide()
                        }
                    }
                ) {
                    CursorListenedIcon(
                        Icons.Power,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        activeTint = if (isCrosshairEnabled) powerButtonEnabledFocused else powerButtonDisabledFocused,
                        inactiveTint = if (isCrosshairEnabled) powerButtonEnabledUnfocused else powerButtonDisabledUnfocused
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Divider(
                    modifier = Modifier.fillMaxWidth(0.8F),
                    thickness = 2.dp,
                    color = accentSecondary
                )
                Spacer(modifier = Modifier.height(6.dp))
                IconButton(
                    onClick = {
                        navigator?.navigateTo(MainScreen(crosshairState))
                    },
                    modifier = Modifier
                ) {
                    CursorListenedIcon(
                        Icons.SettingsSliders,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        activeTint = accentLight,
                        inactiveTint = accentPrimary
                    )
                }
                IconButton(
                    onClick = {
                        navigator?.navigateTo(ColorSettingsScreen(crosshairState))
                    },
                    modifier = Modifier
                ) {
                    CursorListenedIcon(
                        Icons.Palette,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        activeTint = accentLight,
                        inactiveTint = accentPrimary
                    )
                }
                IconButton(
                    onClick = {
                        navigator?.navigateTo(CSGOIntegrationScreen())
                    },
                    modifier = Modifier
                ) {
                    CursorListenedIcon(
                        Icons.CounterStrike,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        activeTint = accentLight,
                        inactiveTint = accentPrimary
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
            ) {
                WindowFrame(onClose)
                Navigator(MainScreen(crosshairState)) {
                    navigator = it
                    CurrentScreen()
                }
            }
        }
    }
}

@Composable
private fun FrameWindowScope.WindowFrame(
    onClose: () -> Unit
) {
    WindowDraggableArea {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(backgroundColorDark),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*Spacer(modifier = Modifier.width(10.dp))*/
            Text(
                text = "Crosshair settings",
                color = accentLight,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Row {
                IconButton(
                    modifier = Modifier.fillMaxHeight(),
                    onClick = { window.minimize() }
                ) {
                    CursorListenedIcon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.WindowMinimize,
                        contentDescription = null,
                        activeTint = accentLight,
                        inactiveTint = accentDark
                    )
                }
                IconButton(
                    onClick = onClose,
                    modifier = Modifier.fillMaxHeight(),
                ) {
                    CursorListenedIcon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Cross,
                        contentDescription = null,
                        activeTint = accentLight,
                        inactiveTint = accentDark
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Composable
fun SliderWithTitle(
    modifier: Modifier = Modifier,
    title: String,
    value: Float,
    range: ClosedFloatingPointRange<Float>,
    colors: SliderColors,
    onValueChange: (Float) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            title,
            style = MaterialTheme.typography.subtitle1,
            color = accentLight
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Slider(
                modifier = Modifier
                    .fillMaxWidth(0.8F)
                    .weight(0.8F, true),
                value = value,
                onValueChange = onValueChange,
                valueRange = range,
                colors = colors
            )
            Text(
                modifier = Modifier.weight(0.2F),
                text = value.toString(),
                color = accentLight,
                maxLines = 1
            )
        }
    }
}
