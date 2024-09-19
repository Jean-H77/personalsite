plugins {
    id("java")
}

group = "org.personalsite"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin:6.3.0")
    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("com.google.inject:guice:7.0.0")
    implementation("com.google.inject.extensions:guice-multibindings:4.2.3")
    implementation("io.github.lukehutch:fast-classpath-scanner:3.1.15")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("org.jdbi:jdbi3-core:3.45.4")
    implementation("org.jdbi:jdbi3-sqlobject:3.45.4")
    implementation("org.postgresql:postgresql:42.6.0")
    runtimeOnly("com.fasterxml.jackson.module:jackson-modules-java8:2.8.5")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}