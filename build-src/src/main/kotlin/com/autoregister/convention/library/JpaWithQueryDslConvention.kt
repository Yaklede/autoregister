package com.autoregister.convention.library

import org.gradle.api.Plugin
import org.gradle.api.Project

class JpaWithQueryDslConvention: Plugin<Project> {
    override fun apply(target: Project) {
        target.apply {
            plugin("org.jetbrains.kotlin.jvm")
            plugin("org.jetbrains.kotlin.kapt")
        }
        val dependencies = target.dependencies
        dependencies.add("implementation", "com.querydsl:querydsl-jpa:5.0.0:jakarta")
        dependencies.add("kapt","com.querydsl:querydsl-apt:5.0.0:jakarta")
        dependencies.add("kapt", "jakarta.persistence:jakarta.persistence-api")
        dependencies.add("kapt", "jakarta.annotation:jakarta.annotation-api")
        dependencies.add("implementation", "org.springframework.boot:spring-boot-starter-data-jpa")
    }
}