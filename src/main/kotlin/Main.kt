
import DI.initKoin
import Data.CSGOEvents
import Data.CrosshairState
import Data.OVERLAY_SIZE
import Data.dataFolderPath
import UI.CrosshairWindow
import UI.MainWindow
import androidx.compose.ui.window.application
import org.koin.java.KoinJavaComponent.inject
import java.awt.Point
import java.awt.Toolkit

fun main() {
    initKoin()

    println("Data folder: $dataFolderPath")

    val csgoEvents: CSGOEvents by inject(CSGOEvents::class.java)
    /*startGSIServer(csgoEvents)*/

    val crosshairState: CrosshairState by inject(CrosshairState::class.java)
    registerMouseEventListener { button ->
        crosshairState.mouseEventsSource.value = button
    }

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