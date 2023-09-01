package UI.Screens

import Data.CrosshairState
import Data.DefaultPadding
import DataStore.ColorValue
import DataStore.Stores.CrosshairDSImpl
import UI.Crosshair
import UI.Theme.backgroundColor
import UI.Theme.standartColors
import UI.Utils.toAwtColor
import UI.Utils.toHsvColor
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.rememberColorPickerState
import org.koin.compose.koinInject

@OptIn(ExperimentalLayoutApi::class)
class ColorSettingsScreen(
    private val crosshairState: CrosshairState
): Screen {

    private val crosshairPanel = Crosshair(crosshairState).apply {
        isOpaque = false
        background = java.awt.Color(0, 0, 0, 0)
    }

    @Composable
    override fun Content() {
        val colorPickerState = rememberColorPickerState(
            crosshairState.color.value.toHsvColor()
        )
        val crosshairSettings: CrosshairDSImpl = koinInject()
        colorPickerState.color.value
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ClassicColorPicker(
                    modifier = Modifier
                        .padding(DefaultPadding.CardDefaultPaddingBig)
                        .size(200.dp),
                    colorPickerState
                ) { hsvColor ->
                    with(hsvColor.toAwtColor()) {
                        crosshairState.color.value = this
                        crosshairSettings.color = ColorValue(
                            red,
                            green,
                            blue,
                            alpha
                        )
                    }


                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    SwingPanel(
                        background = backgroundColor,
                        factory = {
                            crosshairPanel
                        },
                        modifier = Modifier.size(100.dp)
                    ) {
                        with(crosshairPanel) {
                            paintComponents(graphics)
                        }
                    }
                }
            }
            FlowRow(
                modifier = Modifier
                    .padding(DefaultPadding.CardDefaultPaddingBig)
                    .width(200.dp)
            ) {
                standartColors.forEach { color ->
                    ColorItem(
                        modifier = Modifier.size(40.dp),
                        color = color
                    ) {
                        colorPickerState.setValue(color.toHsvColor())
                        with(color.toAwtColor()) {
                            crosshairState.color.value = this
                            crosshairSettings.color = ColorValue(
                                red,
                                green,
                                blue,
                                alpha
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ColorItem(
    modifier: Modifier,
    color: Color,
    onClick: () -> Unit) {
    Box(
        modifier = modifier
            .padding(5.dp)
            .background(color, RoundedCornerShape(5.dp))
            .clickable(onClick = onClick),
    )
}