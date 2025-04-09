#imagen base de OpenJDK para Java 21
FROM openjdk:21-jdk-slim AS build

WORKDIR /app
COPY . .
RUN ./mvnw clean test package -DskipTests=false

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app/target/application-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
