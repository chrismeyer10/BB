pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BahnAndBike"
include(":app")
include(":core:common")
include(":core:model")
include(":core:designsystem")
include(":domain:routing")
include(":data:bike")
include(":data:rail")
include(":data:places")
include(":feature:search")
include(":feature:journey")
include(":feature:settings")
