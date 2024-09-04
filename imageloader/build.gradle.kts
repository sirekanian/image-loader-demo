plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "org.sirekanyan.imageloader"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
        consumerProguardFiles("proguard-consumer.pro")
    }
    buildTypes {
        release {
            isMinifyEnabled = true
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
}

dependencies {

    // androidx
    implementation("androidx.annotation:annotation:1.8.2")

    // ktor
    implementation("io.ktor:ktor-client-okhttp:2.3.12")

    // etc
    implementation("org.slf4j:slf4j-simple:2.0.16")
}
