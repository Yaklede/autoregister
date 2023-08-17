
import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.1.2" apply false
    id("io.spring.dependency-management") version "1.1.2" apply false
    kotlin("jvm") version "1.8.22"
    kotlin("kapt") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22" apply false
    kotlin("plugin.jpa") version "1.8.22" apply false
    id("com.autoregister.spring.boot") apply false
    id("com.autoregister.kotlin") apply false
    id("com.autoregister.querydsl") apply false
    id("com.autoregister.spring.application") apply false
    id("com.autoregister.core") apply false
}

allprojects {
    apply {
        plugin("com.autoregister.kotlin")
        plugin("com.autoregister.spring.boot")
    }
    group = "com.autoregister"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.named("compileJava") {
        inputs.files(tasks.named("processResources"))
    }
}


project(":modules:app") {
    subprojects {
        apply {
            plugin("com.autoregister.spring.application")
        }
    }
}

project(":modules:core") {

    val jar: Jar by tasks
    val bootJar: BootJar by tasks

    bootJar.enabled = false
    jar.enabled = true

    subprojects {
        extensions.configure(AllOpenExtension::class.java) {
            annotations("jakarta.persistence.Entity")
            annotations("jakarta.persistence.Embeddable")
            annotations("jakarta.persistence.MappedSuperClass")
        }
        apply {
            plugin("com.autoregister.core")
        }
    }
}

project(":modules:internal") {
    val jar: Jar by tasks
    val bootJar: BootJar by tasks

    bootJar.enabled = false
    jar.enabled = true

    subprojects {
        apply {
            plugin("com.autoregister.spring.application")
        }
    }
}

