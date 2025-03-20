plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "nathja.finalproject.kiemtraltdd"
    compileSdk = 35

    defaultConfig {
        applicationId = "nathja.finalproject.kiemtraltdd"
        minSdk = 24
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.retrofit)
    implementation(libs.gsonConverter)
    implementation(libs.okhttpLoggingInterceptor)
    implementation(libs.gson)
    implementation(libs.glide)
    implementation(libs.commons.io)
    annotationProcessor(libs.glide.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.startup:startup-runtime:1.1.1")
}