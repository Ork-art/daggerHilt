plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id ("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation ("com.google.dagger:hilt-core:2.48")
    kapt ("com.google.dagger:hilt-compiler:2.48")
    implementation ("com.google.code.gson:gson:2.9.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
}
