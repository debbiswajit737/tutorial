/*plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    // id("com.google.dagger.hilt.android") // Removed: This should be in project-level build.gradle.kts with apply false
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("dagger.hilt.android.plugin") // Correct plugin for app module
}*/
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.20")  // ✅ Match plugin version
}


// keep your android { ... } and dependencies { ... } as is


// keep dependencies {} as they were

android {
    namespace = "com.bpro.app" // Changed namespace
    compileSdk = 36

    defaultConfig {
        applicationId = "com.bpro.app" // Changed applicationId
        minSdk = 24
        targetSdk = 35
        //old
        versionCode = 122
        versionName = "2.1.7"

        //new
       /* versionCode = 127
        versionName = "2.1.9"*/

        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            ndk {
                debugSymbolLevel = "FULL"
            }
            injectBuildConfigFields()
        }
        debug {
            isDebuggable = true
            injectBuildConfigFields()
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    /*kotlinOptions {
        jvmTarget = "17"
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
        }
    }*/
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }

    lint {
        baseline = file("lint-baseline.xml")
        checkReleaseBuilds = false
        abortOnError = false
    }
    /*packagingOptions {
        resources {
            excludes += setOf(
                "mozilla/public-suffix-list.txt",
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt"
            )
        }
    }*/

    packaging {
        resources {
            excludes += setOf(
                "mozilla/public-suffix-list.txt",
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt"
            )
        }
    }




    packaging {
        jniLibs {
            useLegacyPackaging = false
        }
        resources {
            excludes += "/META-INF/NOTICE.md"
        }
    }

    packagingOptions {
        resources {
            excludes += "META-INF/INDEX.LIST"
        }
    }
}

// Helper to inject build config fields from gradle.properties
fun com.android.build.api.dsl.BuildType.injectBuildConfigFields() {
    val keys = listOf(
        "BIG9_MASTER", "BIG9_10_NEW", "BIG9_11", "BIG9_12","BIG9_13",
        "ALGORITHM2", "AES_CODE", "SECRET_KEY", "AES_TRANSFORMATION",
        "AES_KEY", "AES_IV", "API_KEY", "PG_ALGORITHM", "PG_API_KEY",
        "DIGEST", "ALGORITHM", "CLIENT_ID", "API_KEY_AUTH"
    )

    keys.forEach { key ->
        val value = properties[key]?.toString() ?: ""
        buildConfigField("String", key, "\"$value\"") // Corrected quoting
    }
}

dependencies {
    implementation("androidx.activity:activity:1.10.1")
    val hiltVersion = "2.57.1" // ✅ latest stable, supports Kotlin 1.9.x
    val androidxHiltVersion = "1.3.0"

    // Test Dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.android.material:material:1.13.0")

   /* // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.24")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4") // Consider updating
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4") // Consider updating*/



    // Kotlin
   // implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.20")
   // implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.24")
    // implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.24") // Comment out or remove this
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.2.0")  // Add this (or the version you chose)
    // Coroutines (latest stable, compatible with Kotlin 2.0.x)
    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    // Coroutines (update to version compatible with new Kotlin and being resolved)
    /*implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")*/
   /* implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.5")*/

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // AndroidX Core & UI
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0") // Consider updating
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.compose.ui:ui-graphics-android:1.5.4")
    implementation("androidx.activity:activity-ktx:1.8.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0") // Deprecated
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Navigation
    val navVersion = "2.5.2" // Consider updating to a newer version like 2.7.x
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")

    // Paging
    val pagingVersion = "3.2.1"
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")

    // Room
    implementation("androidx.room:room-ktx:2.8.4")
    kapt("androidx.room:room-compiler:2.8.4")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Firebase (using Bill of Materials - BOM)
    implementation(platform("com.google.firebase:firebase-bom:33.1.2")) // Latest BOM
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-database-ktx") // Added -ktx if not already (check if you use -ktx version)

    // Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    implementation("androidx.hilt:hilt-work:$androidxHiltVersion")
    kapt("androidx.hilt:hilt-compiler:$androidxHiltVersion")
    implementation("androidx.hilt:hilt-navigation-fragment:$androidxHiltVersion")


    // Network (Retrofit, OkHttp, Gson)
   /* implementation("com.squareup.retrofit2:retrofit:3.0.0") // Consider 2.11.0 or latest
    implementation("com.squareup.retrofit2:converter-gson:3.0.0") // Consider 2.11.0 or latest
    implementation("com.squareup.okhttp3:logging-interceptor:5.1.0") // Consider stable 4.x or latest 5.x alpha/beta
    implementation("com.google.code.gson:gson:2.13.2")
    implementation("com.squareup.retrofit2:adapter-rxjava2:3.0.0") // If using RxJava2, consider updating if possible*/


    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
// Stable
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
// Stable
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
// If using RxJava2
// OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
// Stable 4.x
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
// Gson
    implementation("com.google.code.gson:gson:2.10.1")
// Latest stable

    // Google Play Services
    implementation("com.google.android.gms:play-services-location:21.3.0") // Check for latest
    implementation("com.google.android.gms:play-services-auth:21.2.0") {
        exclude(group = "com.google.android.gms", module = "play-services-safetynet")
    }
    implementation("com.google.android.gms:play-services-auth-api-phone:18.0.1") {
        exclude(group = "com.google.android.gms", module = "play-services-safetynet")
    }
    implementation("com.google.android.gms:play-services-vision:20.1.3") // Deprecated, consider ML Kit
    implementation("com.google.android.gms:play-services-wallet:19.4.0") // Check for latest

    // UI Libraries
    implementation("com.intuit.sdp:sdp-android:1.1.1")
    implementation("com.intuit.ssp:ssp-android:1.1.1")
    implementation("com.airbnb.android:lottie:6.6.9") // Consider 6.x
    implementation("io.github.chaosleung:pinview:1.4.4")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.mikhaellopez:circularprogressbar:3.1.0")
    implementation("com.github.bumptech.glide:glide:5.0.5")
    kapt("com.github.bumptech.glide:compiler:5.0.5") // Added Glide compiler
    implementation("com.google.android.flexbox:flexbox:3.0.0")
    implementation("com.facebook.shimmer:shimmer:0.5.0")
    implementation("com.github.prolificinteractive:material-calendarview:2.0.1")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.29")

    // CameraX
    val cameraxVersion = "1.5.0" // Check for latest 1.3.x or 1.4.x
    implementation("androidx.camera:camera-core:$cameraxVersion")
    implementation("androidx.camera:camera-camera2:$cameraxVersion")
    implementation("androidx.camera:camera-lifecycle:$cameraxVersion")
    implementation("androidx.camera:camera-video:$cameraxVersion")
    implementation("androidx.camera:camera-view:$cameraxVersion")
    implementation("androidx.camera:camera-extensions:$cameraxVersion")

    // Media3 (ExoPlayer successor)
    implementation("androidx.media3:media3-exoplayer:1.8.0") // Check for latest 1.x
    implementation("androidx.media3:media3-ui:1.8.0") // Check for latest 1.x

    // Utilities
    implementation("com.karumi:dexter:6.2.3") // Dexter is no longer maintained, consider modern permission handling
    implementation("com.google.zxing:core:3.5.3") // Consider 3.5.x
    implementation("com.itextpdf:itext7-core:9.3.0") // Check for latest
    implementation("org.jetbrains:annotations:26.0.2-1")
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("com.google.auth:google-auth-library-oauth2-http:1.39.1")
    //implementation("com.iceteck.silicompressorr:silicompressor:2.2.4")
    implementation("com.otaliastudios:transcoder:0.10.4")
    implementation("com.googlecode.mp4parser:isoparser:1.1.22")

    // AndroidX Misc
    implementation("androidx.window:window:1.4.0")
    implementation("androidx.viewpager2:viewpager2:1.1.0") // Check for latest 1.1.x
    implementation("androidx.security:security-crypto:1.1.0") // Check for latest
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.biometric:biometric:1.1.0") // Very old, consider 1.2.0-alpha05 or stable 1.1.0

    // WorkManager (for background tasks)
    implementation("androidx.work:work-runtime-ktx:2.10.4") // Downgraded WorkManager version

    // Local .aar files
    implementation(files("libs/onboardinglib_V.1.1.13.aar"))
    implementation(files("libs/vm30-payment-sdk-3.0.22.aar"))

    // Debugging
    debugImplementation("com.github.ChuckerTeam.Chucker:library:4.2.0")
    releaseImplementation("com.github.ChuckerTeam.Chucker:library-no-op:4.2.0")

}
/*configurations.all {
    resolutionStrategy {
        force ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.5")
        force ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.5")
    }
}*/
/*
configurations.all {
    resolutionStrategy {
        force("org.jetbrains.kotlin:kotlin-stdlib:2.0.20")
        force("org.jetbrains.kotlin:kotlin-stdlib-jdk7:2.0.20")
        force("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.20")
    }
}
*/


/*configurations.all {
    resolutionStrategy {
        force("org.jetbrains.kotlin:kotlin-stdlib:2.0.20")
        force("org.jetbrains.kotlin:kotlin-stdlib-jdk7:2.0.20")
        force("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.20")
    }
}*/
/*configurations.all {
    exclude(group = "com.google.android.gms", module = "play-services-safetynet")
    resolutionStrategy {
        force("org.jetbrains.kotlin:kotlin-stdlib:1.9.24")
        force("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.24")
        force("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.24")
    }
}*/

/*configurations.all {
    exclude(group = "com.google.android.gms", module = "play-services-safetynet")
    resolutionStrategy {
        force("org.jetbrains.kotlin:kotlin-stdlib:2.0.20")
    }
}*/
kapt {
    correctErrorTypes = true
    javacOptions {
        option("-J--add-exports", "jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED")
        option("-J--add-exports", "jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED")
    }
}
