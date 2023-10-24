plugins {
  alias(libs.plugins.android.library)
  id("org.jetbrains.kotlin.android")
}

android {
  namespace = "com.moneybase.stocktracker.common"
  compileSdk = 34

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
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.4.3"
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui.tooling)
  implementation(libs.androidx.hilt.navigation.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.material.iconsExtended)
  implementation(project(":dtos"))

  testImplementation(libs.junit4)
  testImplementation(libs.google.truth)
  testImplementation(libs.androidx.core.testing)
  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.kotlin.mockito)
  testImplementation("app.cash.turbine:turbine:1.0.0")
}