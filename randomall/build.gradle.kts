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
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT") // Use the appropriate version

    // Optionally, you can add JUnit for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Other dependencies like Guava can be included if needed.
    implementation(libs.guava)
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

// Configure jar task to build a plugin-compatible JAR file.
tasks.named<Jar>("jar") {
    // Add plugin.yml to the root of the JAR to define plugin metadata.
    from("src/main/resources/plugin.yml") {
        into("/")
    }
}

// Plugin metadata for Minecraft (create src/main/resources/plugin.yml)
tasks.processResources {
    filesMatching("plugin.yml") {
        expand("version" to project.version)
    }
}
