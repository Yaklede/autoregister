package com.autoregister.convention.library

import org.gradle.api.Plugin
import org.gradle.api.Project

class SpringBootConvention : Plugin<Project> {
    override fun apply(target: Project) {
        target.apply {
            plugin("org.jetbrains.kotlin.plugin.spring")
            plugin("org.springframework.boot")
            plugin("io.spring.dependency-management")
        }

        target.dependencies.let {
            it.add("implementation", "com.fasterxml.jackson.module:jackson-module-kotlin")
            it.add("implementation", "org.jetbrains.kotlin:kotlin-reflect")
            it.add("testImplementation", "org.springframework.boot:spring-boot-starter-test")
            it.add("testImplementation", "com.h2database:h2")
        }
    }
}