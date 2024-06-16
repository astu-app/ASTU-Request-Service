plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

val ktor_version: String by project

repositories {
    mavenCentral()
}

kotlin{
    jvmToolchain(17)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
