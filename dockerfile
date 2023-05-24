FROM adoptopenjdk:17-jdk-hotspot

WORKDIR /app

COPY moneytransfer-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]