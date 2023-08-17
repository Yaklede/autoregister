package com.autoregister.convention.kotlin

import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinConvention: Plugin<Project> {
    override fun apply(target: Project) {
        target.apply {
            plugin("org.gradle.java-library")
            plugin("org.jetbrains.kotlin.jvm")
            plugin("org.gradle.java")
            plugin("org.jetbrains.kotlin.kapt")
        }
    }
}