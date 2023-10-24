plugins {
  alias(libs.plugins.android.application)
  id("org.jetbrains.kotlin.android")
  kotlin("kapt")
  id("com.google.dagger.hilt.android")
}
android {
  namespace = "com.moneybase.stocktracker"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.moneybase.stocktracker"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
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
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.core.splashscreen)
  implementation(libs.androidx.compose.runtime)
  implementation(libs.androidx.lifecycle.runtimeCompose)
  implementation(libs.androidx.compose.runtime.tracing)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui.tooling)
  implementation(libs.androidx.hilt.navigation.compose)
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.window.manager)
  implementation(libs.androidx.profileinstaller)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.kotlinx.coroutines.guava)
  implementation(libs.coil.kt)
  implementation(platform(libs.androidx.compose.bom))

  implementation(project(":common"))
  implementation(project(":business"))
  implementation(project(":dtos"))
  implementation(project(":stocksListFeature"))
  implementation(project(":networks"))
  implementation(project(":repositories"))

}