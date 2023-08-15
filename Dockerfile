FROM amazoncorretto:17.0.5-al2022-RC-headless
ARG JAR_FILE='/modules/app/admin-application/build/libs/admin-application-0.0.1-SNAPSHOT.jar'
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]