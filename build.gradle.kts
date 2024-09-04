plugins {
    val pluginVersion = "8.6.0"
    val kotlinVersion = "2.0.20"
    id("com.android.library") version pluginVersion apply false
    id("com.android.application") version pluginVersion apply false
    id("org.jetbrains.kotlin.android") version kotlinVersion apply false
    id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion apply false
    id("org.sirekanyan.version-checker") version "1.0.11" apply false
}
