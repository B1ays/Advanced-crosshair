
import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.NativeHookException
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent.NOBUTTON
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener
import kotlin.system.exitProcess

class MouseListener(private val onEvent: (Int) -> Unit): NativeMouseInputListener {
    override fun nativeMousePressed(nativeEvent: NativeMouseEvent?) {
        /*super.nativeMousePressed(nativeEvent)*/
        nativeEvent?.button?.let { onEvent(it) }
    }

    override fun nativeMouseReleased(nativeEvent: NativeMouseEvent?) {
        /*super.nativeMouseReleased(nativeEvent)*/
        onEvent(NOBUTTON)
    }
}

fun registerMouseEventListener(onEvent: (Int) -> Unit) {
    try {
        GlobalScreen.registerNativeHook()
    } catch (ex: NativeHookException) {
        println("There was a problem registering the native hook.")
        println(ex.message)
        exitProcess(1)
    }
    val listener = MouseListener(onEvent)
    GlobalScreen.addNativeMouseListener(listener)
    GlobalScreen.addNativeMouseMotionListener(listener)
}
