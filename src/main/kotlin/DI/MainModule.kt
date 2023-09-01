package DI

import Data.CSGOEvents
import Data.CrosshairState
import DataStore.CrosshairSettings
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.dsl.module
import uk.oczadly.karl.csgsi.GSIServer

val mainModule = module {
    single {
        val crosshairSettings = runBlocking {
            get<DataStore<CrosshairSettings>>(crosshairDSQualifier).data.first()
        }
        CrosshairState(
            crosshairSettings
        )
    }
    single { CSGOEvents() }
    single {
        val csgoEvents: CSGOEvents = get()
        GSIServer
        .Builder(1447) // Port 1337, on all network interfaces
        .registerListener(csgoEvents.listener) // Alternatively, you can call this dynamically on the GSIServer
        .build()
    }
}