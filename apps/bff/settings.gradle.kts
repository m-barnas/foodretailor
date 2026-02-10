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

include("adapters:homepage-ktor-web-adapter")
include("configurations:ktor-configuration")



include("tools:parallel-tools")
