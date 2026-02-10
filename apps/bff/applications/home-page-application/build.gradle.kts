plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":tools:parallel-tools"))
    implementation(libs.kotlinx.coroutines.core)
}