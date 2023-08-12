import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.2" apply false
    id("io.spring.dependency-management") version "1.1.2" apply false
    kotlin("jvm") version "1.8.22"
    kotlin("kapt") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22" apply false
    kotlin("plugin.jpa") version "1.8.22" apply false
}

allprojects {
    subprojects {
        apply {
            plugin("org.jetbrains.kotlin.plugin.spring")
            plugin("org.springframework.boot")
            plugin("io.spring.dependency-management")
            plugin("org.gradle.java-library")
            plugin("org.jetbrains.kotlin.jvm")
            plugin("org.gradle.java")
            plugin("org.jetbrains.kotlin.kapt")
        }
    }

    group = "com.example"
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
}

project(":modules:app") {
    subprojects {
        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-web")
        }
    }
}

project(":modules:core") {
    subprojects {
        extensions.configure(AllOpenExtension::class.java) {
            annotations("jakarta.persistence.Entity")
            annotations("jakarta.persistence.Embeddable")
            annotations("jakarta.persistence.MappedSuperClass")
        }

        dependencies {
            implementation ("com.querydsl:querydsl-jpa:5.0.0:jakarta")
            kapt ("com.querydsl:querydsl-apt:5.0.0:jakarta")
            kapt ("jakarta.annotation:jakarta.annotation-api")
            kapt ("jakarta.persistence:jakarta.persistence-api")

            implementation("org.springframework.boot:spring-boot-starter-data-jpa")
            implementation("org.springframework.boot:spring-boot-starter-security")
            implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
            implementation("org.jetbrains.kotlin:kotlin-reflect")
            runtimeOnly("com.h2database:h2")

            testImplementation("org.springframework.boot:spring-boot-starter-test")
            testImplementation("org.springframework.security:spring-security-test")
        }
    }
}

