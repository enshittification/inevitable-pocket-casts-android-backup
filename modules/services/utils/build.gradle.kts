plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.parcelize)
}

apply(from = "${project.rootDir}/base.gradle")

// utils should have no other module dependencies

android {
    namespace = "au.com.shiftyjelly.pocketcasts.helper"
    buildFeatures {
        buildConfig = true
    }
}
