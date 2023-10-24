pluginManagement {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "StockTracker"
include(":app")
include(":common")
include(":stocksListFeature")
include(":profileFeature")
include(":dtos")
include(":business")
include(":networks")
include(":repositories")
