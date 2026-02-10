plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(project(":tools:parallel-tools"))
    implementation(libs.kotlinx.coroutines.core)
}