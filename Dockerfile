# syntax=docker/dockerfile:1

# --- Build stage: compile and package the executable Spring Boot jar ---
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /workspace

# Warm the dependency cache first so it survives source-only changes.
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw -B -q dependency:go-offline

COPY src/ src/
RUN ./mvnw -B -q clean package -DskipTests \
    && cp target/utilsFile-*.jar app.jar

# --- Runtime stage: distroless JRE, non-root ---
FROM gcr.io/distroless/java21-debian12:nonroot
WORKDIR /app
COPY --from=build /workspace/app.jar app.jar
USER nonroot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
