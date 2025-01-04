plugins {
    // Use the Java plugin instead of application, as we're building a plugin.
    java
}

repositories {
    // Use Maven Central for common dependencies.
    mavenCentral()
    // Add Paper's repository for Minecraft API dependencies.
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    // Include the Paper API for Minecraft plugin development.
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT") // Use the appropriate version

    // Optionally, you can add JUnit for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    // Apply a specific Java toolchain.
    toolchain {
        languageVersion = JavaLanguageVersion.of(17) // Minecraft plugins typically use Java 17
    }
}

// Configure testing to use JUnit Platform
tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    filesMatching("plugin.yml") {
        expand("version" to project.version)
    }
}

tasks.named<Jar>("jar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from("src/main/resources/plugin.yml") {
        into("/")
    }
}