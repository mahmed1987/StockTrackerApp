@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.org.jetbrains.kotlin.android)
  kotlin("kapt")
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "com.moneybase.stocktracker.profilefeature"
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
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.4.3"
  }
}

dependencies {
  implementation(libs.androidx.compose.material3)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.ui.tooling)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.hilt.navigation.compose)
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
  implementation(project(":common"))
  testImplementation(libs.junit4)
  androidTestImplementation(libs.junit)
  androidTestImplementation(libs.androidx.test.espresso.core)
}