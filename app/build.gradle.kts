plugins {
    alias(libs.plugins.androidApplication)

    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.zensorapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.zensorapp"
        minSdk = 24
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

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)

    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    androidTestImplementation(libs.espresso.core)

    implementation("com.google.firebase:firebase-analytics")

    //firestore
    implementation("com.google.firebase:firebase-firestore")

    //firebase auth
    implementation("com.google.firebase:firebase-auth")

    //firebase storage
    implementation("com.google.firebase:firebase-storage")

    //Glide for image loading
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    // Glide's annotation processor (used in the Glide module)
    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2")
}