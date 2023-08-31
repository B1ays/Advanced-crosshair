
import DI.initKoin
import Data.CSGOEvents
import Data.CrosshairState
import Data.OVERLAY_SIZE
import UI.CrosshairWindow
import UI.MainWindow
import androidx.compose.ui.window.application
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent.NOBUTTON
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.java.KoinJavaComponent.inject
import java.awt.Point
import java.awt.Toolkit

fun main() {
    initKoin()

    val csgoEvents: CSGOEvents by inject(CSGOEvents::class.java)
    /*startGSIServer(csgoEvents)*/

    val mouseEvents = MutableStateFlow(NOBUTTON)
    registerMouseEventListener { button ->
        mouseEvents.value = button
    }

    val crosshairState: CrosshairState by inject(CrosshairState::class.java)
    crosshairState.setMouseEventsSource(mouseEvents)

    val screenSize = Toolkit.getDefaultToolkit().screenSize
    val (height, width) = screenSize.height to screenSize.width

    application {
        CrosshairWindow(
            crosshairState,
            csgoEvents,
            Point(
                (width/2)-(OVERLAY_SIZE/2),
                (height/2)-(OVERLAY_SIZE/2)
            )
        )
        MainWindow(::exitApplication)
        /*StatisticWindow(csgoEvents)*/
    }
}