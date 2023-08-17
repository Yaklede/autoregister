package com.autoregister.convention.library

import org.gradle.api.Plugin
import org.gradle.api.Project

class SpringApplicationConvention: Plugin<Project> {
    override fun apply(target: Project) {
        target.apply {
            plugin("com.autoregister.spring.boot")
            plugin("com.autoregister.kotlin")
        }

        target.dependencies.add("implementation", "org.springframework.boot:spring-boot-starter-web")
    }
}