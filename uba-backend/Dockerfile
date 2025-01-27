# Build stage
FROM maven:3.8.6-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Production stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/uba-backend-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"] 