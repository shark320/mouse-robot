plugins {
    kotlin("jvm") version "2.0.20"
    application
}

group = "com.vpavlov.mouse_robot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

// Specify the main class
application {
    mainClass.set("com.vpavlov.mouse_robot.MainKt")
}

// Create an executable JAR
tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.vpavlov.mouse_robot.MainKt"
    }
    from({
        configurations.runtimeClasspath.get().filter { it.exists() }.map { if (it.isDirectory) it else zipTree(it) }
    })
}
