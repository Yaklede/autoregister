dependencies {
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation(project(":modules:core:mysql-datasource"))
    testImplementation("org.springframework.batch:spring-batch-test")
}