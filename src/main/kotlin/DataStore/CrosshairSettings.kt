package DataStore

import androidx.annotation.IntRange
import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class CrosshairSettings(
    val color: ColorValue,
    val size: Float,
    val offsetFromCenter: Float,
    val strokeWidth: Float,
    val typeIndex: Int
)

@Serializable
data class ColorValue(
    @IntRange(from = 0L, to = 255L) val red: Int,
    @IntRange(from = 0L, to = 255L) val green: Int,
    @IntRange(from = 0L, to = 255L) val blue: Int,
    @IntRange(from = 0L, to = 255L) val alpha: Int
) {
    companion object {
        val Green = ColorValue(
            0,
            255,
            0,
            255
        )
    }
}


@OptIn(ExperimentalSerializationApi::class)
class CrosshairSettingsSerializer: Serializer<CrosshairSettings> {
    override val defaultValue: CrosshairSettings = CrosshairSettings(
        color = ColorValue.Green,
        size = 10f,
        offsetFromCenter = 3f,
        strokeWidth = 2f,
        typeIndex = 0
    )

    override suspend fun readFrom(input: InputStream): CrosshairSettings = withContext(Dispatchers.IO) {
        return@withContext try {
            Json.decodeFromStream<CrosshairSettings>(input)
        } catch (e: Exception) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: CrosshairSettings, output: OutputStream) = withContext(Dispatchers.IO) {
        Json.encodeToStream(t, output)
    }

}