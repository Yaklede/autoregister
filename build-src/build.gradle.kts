plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("springBootConvention") {
            id = "com.autoregister.spring.boot"
            implementationClass = "com.autoregister.convention.library.SpringBootConvention"
        }

        create("jpaWithQueryDsl") {
            id = "com.autoregister.querydsl"
            implementationClass = "com.autoregister.convention.library.JpaWithQueryDslConvention"
        }

        create("kotlinConvention") {
            id = "com.autoregister.kotlin"
            implementationClass = "com.autoregister.convention.kotlin.KotlinConvention"
        }

        create("springApplicationConvention") {
            id = "com.autoregister.spring.application"
            implementationClass = "com.autoregister.convention.library.SpringApplicationConvention"
        }

        create("coreModuleConvention") {
            id = "com.autoregister.core"
            implementationClass = "com.autoregister.convention.library.CoreModuleConvention"
        }
    }
}