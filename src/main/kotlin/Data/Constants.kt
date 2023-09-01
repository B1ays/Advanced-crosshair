package Data

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

const val APP_NAME = "Advanced crosshair"

const val OVERLAY_SIZE = 100

const val NETCOMPORT_OPTION = "-netconport 1447"

val dataFolderPath: String = System.getenv("APPDATA") + "\\" + APP_NAME + "\\"

object DefaultPadding {
    val CardDefaultPaddingSmall = PaddingValues(horizontal = 6.dp, 2.dp)
    val CardDefaultPaddingMedium = PaddingValues(horizontal = 12.dp, 3.dp)
    val CardDefaultPaddingBig = PaddingValues(horizontal = 16.dp, 6.dp)
}