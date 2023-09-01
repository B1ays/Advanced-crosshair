package DI

import Data.dataFolderPath
import DataStore.Stores.CrosshairDSImpl
import DataStore.Stores.EnableWithSniperRifleDS
import DataStore.storage
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.File

val preferencesDSQualifier = named("PreferencesDS")
val crosshairDSQualifier = named("crosshairDS")

val dataStoreModule = module {
    single(qualifier = preferencesDSQualifier) {
        PreferenceDataStoreFactory.create {
            File(dataFolderPath, "AppPrefs.preferences_pb")
        }
    }
    single(qualifier = crosshairDSQualifier) {
        DataStoreFactory.create(
            storage = storage
        )
    }
    single {
        CrosshairDSImpl(get(qualifier = crosshairDSQualifier))
    }
    single {
        EnableWithSniperRifleDS(get(preferencesDSQualifier))
    }
}
