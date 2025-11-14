
plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(libs.guava)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "com.taher.App"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<Test> {
    testLogging {
        events("passed", "skipped", "failed", "standardOut", "standardError")
        showExceptions = true
        showCauses = true
        showStackTraces = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

// enable gradle to handle input from user
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

// to enable preview feature of java
tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("--enable-preview")

    // options.compilerArgs.add("-Xlint:all")
    // options.compilerArgs.add("-Xdiags:verbose")
    options.isFailOnError = true
    options.isVerbose = false
}

tasks.withType<JavaExec>().configureEach {
  jvmArgs = listOf("--enable-preview")

  standardOutput = System.out
  errorOutput = System.err

  // jvmArgs("-XX:+ShowCodeDetailsInExceptionMessages")
  logging.captureStandardOutput(LogLevel.INFO)
  logging.captureStandardError(LogLevel.ERROR)
}

// Add this section to ensure the JAR is executable
tasks.jar {
  manifest {
    attributes["Main-Class"] = "com.taher.App"
    attributes["Class-Path"] = configurations.runtimeClasspath.get().joinToString(" ") { it.name }
  }
  // Include dependencies in the JAR
  from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
