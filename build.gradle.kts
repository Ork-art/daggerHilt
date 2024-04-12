// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.1.4" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false

}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
       // classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")

        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.6")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44.2")


    }
}