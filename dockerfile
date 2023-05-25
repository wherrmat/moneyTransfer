FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY target/money_transfer_api.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]