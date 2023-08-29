package Data
import androidx.compose.ui.input.key.KeyEvent
import kotlinx.coroutines.flow.MutableStateFlow
import java.awt.Color

class CrosshairState {

    private var _mouseEventsSource: MutableStateFlow<Int>? = null

    private val _isShowed = MutableStateFlow(true)
    private val _color = MutableStateFlow(Color.GREEN)
    private val _lineLength = MutableStateFlow(10)
    private val _offsetFromCenter = MutableStateFlow(3)
    private val _strokeWidth = MutableStateFlow(2F)
    private val _type = MutableStateFlow<CrosshairType>(CrosshairType.Standart)

    val isShowed: MutableStateFlow<Boolean>
        get() = _isShowed
    val color: MutableStateFlow<Color>
        get() = _color
    val lineLength: MutableStateFlow<Int>
        get() = _lineLength
    val offsetFromCenter: MutableStateFlow<Int>
        get() = _offsetFromCenter
    val strokeWidth:  MutableStateFlow<Float>
        get() = _strokeWidth
    val type:  MutableStateFlow<CrosshairType>
        get() = _type
    val mouseEventsSource: MutableStateFlow<Int>?
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