FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copia o arquivo JAR correto
COPY target/hubspot-integration-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]