package DI

import Data.CSGOEvents
import Data.CrosshairState
import org.koin.dsl.module

val mainModule = module {
    single { CrosshairState() }
    single { CSGOEvents() }
}