package Data
import DataStore.CrosshairSettings
import androidx.compose.ui.input.key.KeyEvent
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent.NOBUTTON
import kotlinx.coroutines.flow.MutableStateFlow
import java.awt.Color

class CrosshairState(crosshairSettings: CrosshairSettings) {

    private var _mouseEventsSource: MutableStateFlow<Int> = MutableStateFlow(NOBUTTON)

    private val _isShowed = MutableStateFlow(true)
    private val _color = MutableStateFlow(
        with(crosshairSettings.color) {
            Color(
                red,
                green,
                blue,
                alpha
            )
        }
    )
    private val _lineLength = MutableStateFlow(
        crosshairSettings.size
    )
    private val _offsetFromCenter = MutableStateFlow(
        crosshairSettings.offsetFromCenter
    )
    private val _strokeWidth = MutableStateFlow(
        crosshairSettings.strokeWidth
    )
    private val _type = MutableStateFlow(
        CrosshairType.getByIndex(crosshairSettings.typeIndex)
    )

    val isShowed: MutableStateFlow<Boolean>
        get() = _isShowed
    val color: MutableStateFlow<Color>
        get() = _color
    val lineLength: MutableStateFlow<Float>
        get() = _lineLength
    val offsetFromCenter: MutableStateFlow<Float>
        get() = _offsetFromCenter
    val strokeWidth: MutableStateFlow<Float>
        get() = _strokeWidth
    val type: MutableStateFlow<CrosshairType>
        get() = _type
    val mouseEventsSource: MutableStateFlow<Int>
        get() = _mouseEventsSource

    fun onKeyEvent(keyEvent: KeyEvent): Boolean {
        return true
    }

    fun hide() {
        _isShowed.value = false
    }

    fun show() {
        _isShowed.value = true
    }

    fun setColor(color: Color) {
        _color.value = color
    }

    fun setType(type: CrosshairType) {
        _type.value = type
    }

    fun setMouseEventsSource(source: MutableStateFlow<Int>) {
        _mouseEventsSource = source
    }

}