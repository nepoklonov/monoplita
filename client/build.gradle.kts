plugins {
    kotlin("js")
}

val kotlinxHtmlVersion = project.property("kotlinx.html.version") as String
val kotlinxSerializationVersion = project.property("kotlinx.serialization.version") as String
val kotlinxCoroutinesVersion = project.property("kotlinx.coroutines.version") as String
val kotlinWrappersVersion = project.property("kotlin.wrappers.version") as String

fun kotlinWrappers(target: String): String = "org.jetbrains.kotlin-wrappers:kotlin-$target"

repositories {
    mavenCentral()
}


kotlin {
    js {
        useCommonJs()
        browser {
            runTask {
                devServer?.run {
                    proxy = mutableMapOf("/" to "http://localhost:5793")
                    port = 3000
                }
            }
        }
        binaries.executable()
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-js:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$kotlinxCoroutinesVersion")

    implementation(project(":shared"))

    implementation("org.jetbrains.kotlinx:kotlinx-html:$kotlinxHtmlVersion")

    implementation(enforcedPlatform(kotlinWrappers("wrappers-bom:${kotlinWrappersVersion}")))
    implementation(kotlinWrappers("react"))
    implementation(kotlinWrappers("react-dom"))
    implementation(kotlinWrappers("react-router-dom"))
    implementation(kotlinWrappers("styled"))
    implementation(kotlinWrappers("extensions"))
    implementation(kotlinWrappers("css"))

    testImplementation(kotlin("test"))
}