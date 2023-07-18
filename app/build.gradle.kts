@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "com.mecheka.calculategas"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.mecheka.calculategas"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    sourceSets {
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(libs.androidx.corektx)
    implementation(platform(libs.kotlin.bom))
    implementation(libs.androidx.lifecyleRuntimeKtx)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.composeDebug)
}