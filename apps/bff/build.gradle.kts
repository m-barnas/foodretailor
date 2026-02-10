plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    idea
}

allprojects {
    group = "com.foodretailor.bff"
    version = "0.0.1"
}

subprojects {
    apply(plugin = "idea")

    plugins.withId("org.jetbrains.kotlin.jvm") {
        extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension>("kotlin") {
            jvmToolchain(25)
        }
    }

    repositories {
        mavenCentral()
    }
}
