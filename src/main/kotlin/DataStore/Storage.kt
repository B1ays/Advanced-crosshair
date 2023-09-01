package DataStore

import Data.dataFolderPath
import java.io.File

val storage = WindowsFileStorage(
    serializer = CrosshairSettingsSerializer(),
    produceFile = {
        File(dataFolderPath, "CrosshairSettings.json")
    }
)