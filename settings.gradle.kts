rootProject.name = "autoregister"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        mavenCentral()
    }
}

include(autoIncludeWithPathName("app") {
    listOf("admin-application", "web-application-common", "batch-application")
})

include(autoIncludeWithPathName("core") {
    listOf("common", "mysql-datasource")
})

include(autoIncludeWithPathName("internal") {
    listOf("naver-api")
})

fun autoIncludeWithPathName(path: String, modules: (Unit) -> List<String>): List<String> {
    return modules.invoke(Unit).map {
        module -> ":modules:$path:$module"
    }
}