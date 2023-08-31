package UI.Screens

import Data.CrosshairState
import Data.CrosshairType
import Data.DefaultPadding
import UI.Components.CustomTabs.CustomTab
import UI.Components.CustomTabs.CustomTabRow
import UI.Icons.CrosshairTypeIcons
import UI.Icons.crosshairtypeicons.CrosshairCircle
import UI.Icons.crosshairtypeicons.CrosshairStandart
import UI.Icons.crosshairtypeicons.CrosshairTShaped
import UI.Icons.crosshairtypeicons.CrosshairTriangle
import UI.SliderWithTitle
import UI.Theme.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

class MainScreen(private val crosshairState: CrosshairState): Screen {
    @Composable
    override fun Content() {
        val crosshairType by crosshairState.type.collectAsState()

        CustomTabRow(
            modifier = Modifier
                .padding(DefaultPadding.CardDefaultPaddingBig)
                .height(50.dp)
                .fillMaxWidth(),
            selectedTabIndex = crosshairType.index,
            backgroundColor = backgroundColorDark,
            contentColor = accentSecondary,
            shape = cardShapeMid,
            indicatorPadding = 4.dp
        ) {
            CustomTab(
                selected = crosshairType is CrosshairType.Standart,
                onClick = { crosshairState.setType(CrosshairType.Standart) },
                selectedContentColor = accentLight,
                unselectedContentColor = accentLight
            ) {
                Icon(CrosshairTypeIcons.CrosshairStandart, null)
            }
            CustomTab(
                selected = crosshairType is CrosshairType.TShaped,
                onClick = { crosshairState.setType(CrosshairType.TShaped) },
                selectedContentColor = accentLight,
                unselectedContentColor = accentLight
            ) {
                Icon(CrosshairTypeIcons.CrosshairTShaped, null)
            }
            CustomTab(
                selected = crosshairType is CrosshairType.Triangle,
                onClick = { crosshairState.setType(CrosshairType.Triangle) },
                selectedContentColor = accentLight,
                unselectedContentColor = accentLight
            ) {
                Icon(CrosshairTypeIcons.CrosshairTriangle, null)
            }
            CustomTab(
                selected = crosshairType is CrosshairType.Circle,
                onClick = { crosshairState.setType(CrosshairType.Circle) },
                selectedContentColor = accentLight,
                unselectedContentColor = accentLight
            ) {
                Icon(CrosshairTypeIcons.CrosshairCircle, null)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        SliderWithTitle(
            modifier = Modifier
                .padding(DefaultPadding.CardDefaultPaddingMedium)
                .fillMaxWidth(0.7F),
            title = "Size",
            value = crosshairState.lineLength.collectAsState().value,
            range = 0F..100F,
            colors = SliderDefaults.colors(thumbColor = accentPrimary, activeTrackColor = accentDark)
        ) {
            crosshairState.lineLength.value = it
        }
        SliderWithTitle(
            modifier = Modifier
                .padding(DefaultPadding.CardDefaultPaddingMedium)
                .fillMaxWidth(0.7F),
            title = "Offset from center",
            value = crosshairState.offsetFromCenter.collectAsState().value,
            range = 0F..20F,
            colors = SliderDefaults.colors(thumbColor = accentPrimary, activeTrackColor = accentDark)
        ) {
            crosshairState.offsetFromCenter.value = it
        }
        SliderWithTitle(
            modifier = Modifier
                .padding(DefaultPadding.CardDefaultPaddingMedium)
                .fillMaxWidth(0.7F),
            title = "Stroke width",
            value = crosshairState.strokeWidth.collectAsState().value,
            range = 0F..10F,
            colors = SliderDefaults.colors(thumbColor = accentPrimary, activeTrackColor = accentDark)
        ) {
            crosshairState.strokeWidth.value = it
        }
    }
}