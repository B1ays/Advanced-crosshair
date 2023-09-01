package DataStore.Stores

import DataStore.ColorValue
import DataStore.CrosshairSettings
import androidx.datastore.core.DataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CrosshairDSImpl(private val crosshairDS: DataStore<CrosshairSettings>): CoroutineScope {
    override val coroutineContext = Dispatchers.IO

    val colorFlow = crosshairDS.data.map {
        it.color
    }
    var color: ColorValue
        get() = runBlocking { colorFlow.first() }
        set(value) {
            launch {
                crosshairDS.updateData {
                    it.copy(color = value)
                }
            }
        }

    val sizeFlow = crosshairDS.data.map {
        it.size
    }
    var size: Float
        get() = runBlocking { sizeFlow.first() }
        set(value) {
            launch {
                crosshairDS.updateData {
                    it.copy(size = value)
                }
            }
        }

    val offsetFlow = crosshairDS.data.map {
        it.offsetFromCenter
    }
    var offset: Float
        get() = runBlocking { offsetFlow.first() }
        set(value) {
            launch {
                crosshairDS.updateData {
                    it.copy(offsetFromCenter = value)
                }
            }
        }

    val strokeWidthFlow = crosshairDS.data.map {
        it.strokeWidth
    }
    var strokeWidth: Float
        get() = runBlocking { strokeWidthFlow.first() }
        set(value) {
            launch {
                crosshairDS.updateData {
                    it.copy(strokeWidth = value)
                }
            }
        }

    val typeFlow = crosshairDS.data.map {
        it.typeIndex
    }
    var type: Int
        get() = runBlocking { typeFlow.first() }
        set(value) {
            launch {
                crosshairDS.updateData {
                    it.copy(typeIndex = value)
                }
            }
        }
}