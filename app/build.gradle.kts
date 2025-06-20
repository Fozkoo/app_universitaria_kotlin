plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.app_infounsada"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.app_infounsada"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
        viewBinding = true
    }
}

dependencies {
    // Dependencias que ya tenías (sin cambios)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Picasso (manteniendo tu versión)
    implementation("com.squareup.picasso:picasso:2.71828")

    // Retrofit (sin cambios en versiones)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Nuevas dependencias agregadas (solo lo esencial)
    implementation("com.squareup.okhttp3:okhttp:4.12.0") // Requerido por Retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0") // Para debug
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4") // Versión actualizada

    // Testing (sin cambios)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}