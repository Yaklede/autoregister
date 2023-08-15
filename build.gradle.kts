
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

        dependencies {
            runtimeOnly("com.mysql:mysql-connector-j")
            kapt("org.springframework.boot:spring-boot-configuration-processor")
            annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        }
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
        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-web")
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

        dependencies {
            //DB
            implementation ("com.querydsl:querydsl-jpa:5.0.0:jakarta")
            kapt ("com.querydsl:querydsl-apt:5.0.0:jakarta")
            kapt ("jakarta.annotation:jakarta.annotation-api")
            kapt ("jakarta.persistence:jakarta.persistence-api")
            //Spring
            implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//            implementation("org.springframework.boot:spring-boot-starter-security")
            implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
            implementation("org.jetbrains.kotlin:kotlin-reflect")

            //test
            testImplementation("org.springframework.boot:spring-boot-starter-test")
            testImplementation("org.springframework.security:spring-security-test")
        }
    }
}

project(":modules:internal") {
    val jar: Jar by tasks
    val bootJar: BootJar by tasks

    bootJar.enabled = false
    jar.enabled = true

    subprojects {
        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
            implementation("org.jetbrains.kotlin:kotlin-reflect")

            testImplementation("org.springframework.boot:spring-boot-starter-test")
        }
    }
}

