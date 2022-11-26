rootProject.name = "monoplita"

pluginManagement {
    resolutionStrategy {
        repositories {
            gradlePluginPortal()
            mavenCentral()
        }
    }
}

include("shared")
include("client")
include("server")