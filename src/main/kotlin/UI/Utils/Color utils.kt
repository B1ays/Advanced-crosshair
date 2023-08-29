package UI.Utils

import androidx.compose.ui.graphics.Color
import com.github.ajalt.colormath.model.HSV
import com.github.ajalt.colormath.model.RGB
import com.godaddy.android.colorpicker.HsvColor
import com.godaddy.android.colorpicker.HsvColor.Companion.toColor

fun java.awt.Color.toComposeColor(): Color {
    return Color(
        red, green, blue, alpha
    )
}

fun Color.toAwtColor(): java.awt.Color {
    return java.awt.Color(
        red, green, blue, alpha
    )
}

fun java.awt.Color.toHsvColor(): HsvColor {
    return RGB(
        red/255F,
        green/255F,
        blue/255F,
        alpha/255F
    ).toHSV().toColor()
}

fun Color.toHsvColor(): HsvColor {
    return RGB(
        red,
        green,
        blue,
        alpha
    ).toHSV().toColor()
}

fun HsvColor.toAwtColor(): java.awt.Color {
    val hsv = HSV(hue, saturation, value, alpha)
    val rgb = hsv.toSRGB()
    try {
        return java.awt.Color(rgb.redInt, rgb.greenInt, rgb.blueInt, rgb.alphaInt)
    } catch (e: Exception) {
        println("Red: ${rgb.redInt}, green: ${rgb.greenInt}, blue: ${rgb.blueInt}, alpha: ${rgb.alphaInt}")
        return java.awt.Color.WHITE
    }
}