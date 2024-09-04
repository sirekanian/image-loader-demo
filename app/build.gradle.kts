plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.sirekanyan.version-checker")
}

android {
    namespace = "org.sirekanyan.imageloader.demo"
    compileSdk = 34
    defaultConfig {
        applicationId = "org.sirekanyan.imageloader.demo"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        allWarningsAsErrors = true
    }
    lint {
        warningsAsErrors = true
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // androidx
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // ktor
    implementation("io.ktor:ktor-client-okhttp:2.3.12")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.12")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.12")

    // glide / imageloader
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation(project(":imageloader"))

    // etc
    implementation("org.slf4j:slf4j-simple:2.0.16")
}
