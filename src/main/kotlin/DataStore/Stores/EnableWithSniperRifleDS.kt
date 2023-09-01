package DataStore.Stores

import DataStore.Base.BaseDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey

class EnableWithSniperRifleDS(dataStore: DataStore<Preferences>): BaseDataStore<Boolean>(dataStore) {
    override val KEY: Preferences.Key<Boolean> = booleanPreferencesKey("EnableCrosshairOnlyWithSniperRifle")
    override val DEFAULT_VALUE: Boolean = false
}