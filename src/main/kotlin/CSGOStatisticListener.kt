import Data.CSGOEvents
import uk.oczadly.karl.csgsi.GSIListener
import uk.oczadly.karl.csgsi.GSIServer
import uk.oczadly.karl.csgsi.GameStateContext
import uk.oczadly.karl.csgsi.config.GSIConfig
import uk.oczadly.karl.csgsi.config.GameNotFoundException
import uk.oczadly.karl.csgsi.state.GameState
import uk.oczadly.karl.csgsi.state.MapState
import uk.oczadly.karl.csgsi.state.ProviderState
import java.io.IOException
import java.nio.file.Path


// Build the configuration for our service
var config = GSIConfig()
    .setLocalURI(1447) // Server on localhost:1337
    .setTimeoutPeriod(1.0)
    .setBufferPeriod(0.5)
    .withAllDataComponents() // Or specify which using withDataComponents(...)

var listener = GSIListener { state: GameState, context: GameStateContext ->
    // Access state information with the 'state' object...
    println("New state from game client address " + context.address.hostAddress)
    state.provider.ifPresent { provider: ProviderState ->
        println(
            "Client SteamID: " + provider.clientSteamId
        )
    }
    state.map
        .ifPresent { map: MapState -> println("Current map: " + map.name) }
}

fun startGSIServer(csgoEvents: CSGOEvents) {
    val server = GSIServer
        .Builder(1447) // Port 1337, on all network interfaces
        .registerListener(csgoEvents.listener) // Alternatively, you can call this dynamically on the GSIServer
        .build()
    try {
        // Automatically locates the game directory and creates the configuration file
        val output: Path = config.writeFile("test_service")
        println("Written config file: $output")
    } catch (e: GameNotFoundException) {
        // Either CSGO or Steam installation directory could not be located
        System.err.println("Couldn't locate CSGO installation: " + e.message)
    } catch (e: IOException) {
        System.err.println("Couldn't write to configuration file.")
    }
    try {
        server.start() // Start the server (runs in a separate thread)
        println("Server started. Listening for state data...")
    } catch (e: IOException) {
        e.printStackTrace()
    }
}