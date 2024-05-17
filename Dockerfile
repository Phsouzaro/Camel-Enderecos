# Stage 1: Build the application
FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

# Stage 2: Create the Docker image with Java 11 runtime
FROM openjdk:11-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
