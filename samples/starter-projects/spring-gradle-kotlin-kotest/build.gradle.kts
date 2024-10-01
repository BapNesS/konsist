plugins {
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.spring") version "2.0.20"
    `jvm-test-suite`
}

group = "com.sample"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    // The 'api' is used, so classes are available in the konsistTest source set
    api("org.springframework.boot:spring-boot-starter")

    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

kotlin {
    jvmToolchain(21)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<Test> {
    useJUnitPlatform()
}

testing {
    suites {
        register("konsistTest", JvmTestSuite::class) {
            dependencies {
                // Add 'main' source set dependency
                implementation(project())

                // Add Konsist dependency
                implementation("com.lemonappdev:konsist:0.16.1")

                // Add Kotest dependency
                implementation("io.kotest:kotest-runner-junit5-jvm:5.9.1")
            }
        }
    }
}

// Optional block to run Konsist tests together with the Gradle 'check' task
tasks.named("check") {
    dependsOn(testing.suites.named("konsistTest"))
}
