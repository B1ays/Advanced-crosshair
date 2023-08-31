
import uk.oczadly.karl.csgsi.config.DataComponent
import uk.oczadly.karl.csgsi.config.GSIConfig

// Build the configuration for our service
val config = GSIConfig()
    .setLocalURI(1447) // Server on localhost:1337
    .setTimeoutPeriod(0.0)
    .setBufferPeriod(0.1)
    .withDataComponents(
        DataComponent.PLAYER_WEAPONS,
        DataComponent.PLAYER_STATE
    ) // Or specify which using withDataComponents(...)