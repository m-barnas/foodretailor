rootProject.name = "bff"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}


include("applications:home-page-application")

include("adapters:store-adapter")
include("adapters:user-account-adapter")
include("adapters:token-adapter")

include("tools:parallel-tools")
