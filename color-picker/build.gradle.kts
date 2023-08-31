plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
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
    implementation(compose.desktop.currentOs)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.7.3")

    implementation("com.github.ajalt.colormath:colormath:3.3.2")
    implementation("com.github.ajalt.colormath:colormath-ext-jetpack-compose:3.3.2")
}
