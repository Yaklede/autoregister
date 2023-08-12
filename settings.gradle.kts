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
    listOf("admin-application")
})

include(autoIncludeWithPathName("core") {
    listOf("common","naver-api","mysql-datasource")
})

fun autoIncludeWithPathName(path: String, modules: (Unit) -> List<String>): List<String> {
    return modules.invoke(Unit).map {
        module -> ":modules:$path:$module"
    }
}