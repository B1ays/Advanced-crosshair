import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.9.0"
}

group = "ru.blays"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    maven("https://jitpack.io")
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)

    // Native Input events hook
    implementation("com.github.kwhat:jnativehook:2.2.2")

    // Kotlin
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.6.0")

    // Java native access
    implementation("net.java.dev.jna:jna-platform:5.13.0")

    // Koin
    implementation("io.insert-koin:koin-core:3.4.3")
    implementation("io.insert-koin:koin-compose:1.0.4")
    implementation("io.insert-koin:koin-core-coroutines:3.4.3")

    // Voyager
    implementation("cafe.adriel.voyager:voyager-navigator:1.0.0-rc06")
    implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:1.0.0-rc06")
    implementation("cafe.adriel.voyager:voyager-tab-navigator:1.0.0-rc06")
    implementation("cafe.adriel.voyager:voyager-transitions:1.0.0-rc06")

    // color math
    implementation("com.github.ajalt.colormath:colormath:3.3.2")
    implementation("com.github.ajalt.colormath:colormath-ext-jetpack-compose:3.3.2")

    // Cs:go integration
    implementation("uk.oczadly.karl:csgo-gsi:1.6.0")
    implementation("com.google.code.gson:gson:2.8.6")

    // DataStore
    implementation("androidx.datastore:datastore-preferences-core:1.1.0-alpha04")

    implementation(project(":color-picker"))
}



compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Exe)
            packageName = "Advanced crosshair"
            packageVersion = "1.0.0"
        }
    }
}
