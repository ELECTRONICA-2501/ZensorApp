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
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)

    // Firebase BOM
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))

    // Firebase Analytics
    implementation("com.google.firebase:firebase-analytics-ktx")

    //Firebase Storage
    implementation("com.google.firebase:firebase-storage-ktx")



    // Firebase Firestore
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Firebase Authentication
    implementation("com.google.firebase:firebase-auth")

    // Firebase Realtime Database
    implementation("com.google.firebase:firebase-database-ktx")

    // Optional: Firebase Functions
    implementation("com.google.firebase:firebase-functions-ktx")

    // Glide for image loading
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")

    androidTestImplementation(libs.espresso.core)
}
