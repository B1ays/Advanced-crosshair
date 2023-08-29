package DI

import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(mainModule)
}