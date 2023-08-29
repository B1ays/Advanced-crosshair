package UI.Utils

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

inline fun <reified T: Screen> Navigator.navigateTo(destination: T) {
    if (
        items.any { it::class == T::class }
    ) {
        popUntil { screen ->
            screen::class == destination::class
        }
    } else {
        push(destination)
    }
}