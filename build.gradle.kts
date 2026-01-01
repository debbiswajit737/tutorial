plugins {
    id("com.android.application") version "8.6.0" apply false
    id("com.android.library") version "8.6.0" apply false
    id("org.jetbrains.kotlin.android") version "2.2.0" apply false
    id("org.jetbrains.kotlin.kapt") version "2.2.0" apply false
    id("com.google.dagger.hilt.android") version "2.57.1" apply false

}


buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.1")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
    }
}




/*buildscript {
    dependencies {
        // classpath("com.android.tools.build:gradle:3.2.0") // Commented out, likely redundant
        classpath("com.google.gms:google-services:4.4.1")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
   // id("com.android.application") version "8.2.0" apply false
    //id ("com.android.library") version "8.4.0" apply false // Updated to 8.4.0
   // id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    //id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    *//*id ("com.google.dagger.hilt.android") version "2.44.2" apply false // Downgraded to 2.44.2
    id("com.android.application") version "8.4.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.9.24" apply false*//*
    id("com.android.application") version "8.1.3" apply false
   // id("com.android.application") version "8.13.0" apply false
    id("com.android.library") version "8.13.0" apply false
   // id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    id("com.google.dagger.hilt.android") version "2.57.1" apply false
    //id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id ("org.jetbrains.kotlin.android") version "2.0.20"
}*/
