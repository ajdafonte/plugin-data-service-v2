plugins {
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    group = "com.npaw.techtest"
    version = "1.0-SNAPSHOT"
}

repositories {
    mavenCentral()
}

ext {
    // IMPORTANT: Override JUnit BOM from spring dependency management
    // See: https://stackoverflow.com/questions/54598484/gradle-5-junit-bom-and-spring-boot-incorrect-versions
    set("junit-jupiter.version", "5.4.0")
}

dependencies {
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.4.0")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.4.0")
}

tasks {
    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform()
    }
}