plugins {
    java
    id("com.github.johnrengelman.shadow") version ("7.1.2")
}

group = "pl.minus1ms"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.ow2.asm:asm-tree:9.4")
    implementation("org.ow2.asm:asm-commons:9.4")
    implementation("com.github.Col-E:CAFED00D:1.10.2")
    implementation("commons-io:commons-io:2.11.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveBaseName.set("jcompat")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "pl.minus1ms.jcompat.Jcompat"))
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}