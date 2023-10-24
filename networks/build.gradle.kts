@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.org.jetbrains.kotlin.android)
  kotlin("kapt")
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "com.moneybase.stocktracker.networks"
  compileSdk = 33

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }
  buildFeatures {
    buildConfig = true
  }
  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
    debug {
      buildConfigField("String", "API_ENDPOINT", "\"https://api.twelvedata.com/\"")
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

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
  implementation(libs.retrofit.core)
  implementation(libs.retrofit.convertor.gson)
  implementation(libs.retrofit.convertor.scalar)
  implementation(libs.okhttp.logging)
  api(project(":dtos"))

}