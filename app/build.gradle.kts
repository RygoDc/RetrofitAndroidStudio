plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.rygodc.retrofitexample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rygodc.retrofitexample"
        minSdk = 30
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

// com.squareup.retrofit2:retrofit:2.11.0
// com.google.code.gson:gson:2.11.0
// com.squareup.retrofit2:converter-gson:2.11.0
// com.squareup.okhttp3:okhttp:4.12.0
// com.squareup.okhttp3:logging-interceptor:4.12.0
// com.github.bumptech.glide:glide:4.15.1
// com.github.bumptech.glide:compiler:4.15.1



dependencies {
    implementation(libs.glide)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}