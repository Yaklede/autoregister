FROM amazoncorretto:17.0.5-al2022-RC-headless
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]