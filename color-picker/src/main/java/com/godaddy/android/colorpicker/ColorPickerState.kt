package com.godaddy.android.colorpicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable

class ColorPickerState(initialColor: HsvColor) {
    val color = mutableStateOf(initialColor)

    fun setValue(hsvColor: HsvColor) {
        color.value = hsvColor
    }

    companion object {
        val Saver: Saver<ColorPickerState, *> = listSaver(
            save = {
                listOf(
                    it.color.value.hue,
                    it.color.value.saturation,
                    it.color.value.value,
                    it.color.value.alpha
                )
            },
            restore = {
                ColorPickerState(
                    HsvColor(
                        it[0],
                        it[1],
                        it[2],
                        it[3]
                    )
                )
            }
        )
    }

}

@Composable
fun rememberColorPickerState(initialColor: HsvColor): ColorPickerState {
    return rememberSaveable(saver = ColorPickerState.Saver) {
        ColorPickerState(initialColor)
    }
}