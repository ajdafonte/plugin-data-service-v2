plugins {
    java
    application
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

application {
    group = "com.npaw.techtest"
    version = "0.0.1-SNAPSHOT"
    mainClassName = "com.npaw.techtest.plugindataservice.PluginDataServiceStartup"
}

repositories {
    mavenCentral()
}

ext {
    // IMPORTANT: Override JUnit BOM from spring dependency management
    // See: https://stackoverflow.com/questions/54598484/gradle-5-junit-bom-and-spring-boot-incorrect-versions
    set("junit-jupiter.version", "5.4.0")
    this["slf4jVersion"] = "1.7.26"
    this["jettyVersion"] = "9.4.16.v20190411"
    this["jerseyVersion"] = "2.28"
    this["lombokVersion"] = "1.18.6"
    this["junitVersion"] = "5.4.0"
}

dependencies {
    implementation("org.slf4j", "slf4j-api", ext["slf4jVersion"].toString())
    implementation("org.slf4j", "slf4j-simple", ext["slf4jVersion"].toString())

    implementation("org.eclipse.jetty","jetty-server", ext["jettyVersion"].toString())
    implementation("org.eclipse.jetty","jetty-servlet", ext["jettyVersion"].toString())
    implementation("org.eclipse.jetty","jetty-jmx", ext["jettyVersion"].toString())

    implementation("org.glassfish.jersey.core", "jersey-server", ext["jerseyVersion"].toString())
    implementation("org.glassfish.jersey.containers", "jersey-container-servlet-core", ext["jerseyVersion"].toString())
    implementation("org.glassfish.jersey.containers", "jersey-container-jetty-http", ext["jerseyVersion"].toString())
    implementation("org.glassfish.jersey.ext", "jersey-bean-validation", ext["jerseyVersion"].toString())

    implementation("org.glassfish.jersey.media", "jersey-media-json-jackson", ext["jerseyVersion"].toString())
    implementation("org.glassfish.jersey.inject", "jersey-hk2", ext["jerseyVersion"].toString())

    compileOnly("org.projectlombok","lombok", ext["lombokVersion"].toString())
    annotationProcessor("org.projectlombok","lombok", ext["lombokVersion"].toString())

    testImplementation("org.junit.jupiter", "junit-jupiter-api", ext["junitVersion"].toString())
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", ext["junitVersion"].toString())
}

val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-fat"
    manifest {
        attributes["Implementation-Title"] = "Plugin Data Service"
        attributes["Implementation-Version"] = version
        attributes["Main-Class"] = "com.npaw.techtest.plugindataservice.PluginDataServiceStartup"
    }
    from(configurations.runtime.map({ if (it.isDirectory) it else zipTree(it) }))
    with(tasks["jar"] as CopySpec)
}

tasks {
    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform()
    }

    "build" {
        dependsOn(fatJar)
    }
}