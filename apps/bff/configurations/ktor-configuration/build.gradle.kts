plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":applications:home-page-application"))
    implementation(project(":adapters:homepage-ktor-web-adapter"))
    implementation(project(":adapters:store-adapter"))
    implementation(project(":adapters:token-adapter"))
    implementation(project(":adapters:user-account-adapter"))

    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.logback.classic)
}