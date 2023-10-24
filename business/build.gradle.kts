@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.org.jetbrains.kotlin.android)
  id("com.google.dagger.hilt.android")
  kotlin("kapt")
}

android {
  namespace = "com.moneybase.stocktracker.business"
  compileSdk = 33

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
}

dependencies {
  implementation(libs.hilt.android)
  implementation(project(":repositories"))
  api(project(":dtos"))
  implementation(libs.androidx.hilt.navigation.compose)
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)

}