plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("application")
    id("distribution")
}

val kotlinVersion = project.property("kotlin.version") as String
val kotlinxSerializationVersion = project.property("kotlinx.serialization.version") as String
val ktorVersion = project.property("ktor.version") as String
val kotlinWrappersVersion = project.property("kotlin.wrappers.version") as String
val logbackVersion = project.property("logback.version") as String

fun kotlinWrappers(target: String): String = "org.jetbrains.kotlin-wrappers:kotlin-$target"

dependencies {
    implementation(project(":shared"))

    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")

    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-html-builder:$ktorVersion")
    runtimeOnly("ch.qos.logback:logback-classic:$logbackVersion")

    implementation(enforcedPlatform(kotlinWrappers("wrappers-bom:${kotlinWrappersVersion}")))
    implementation(kotlinWrappers("css"))

    testImplementation(kotlin("test"))
}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes("Main-Class" to application.mainClass.get())
    }
    from(
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    )
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}