# Build stage
FROM eclipse-temurin:17-jdk AS builder 

WORKDIR /build 

COPY pom.xml .
COPY src ./src 

RUN apt-get update && apt-get install -y maven && \
    mvn clean package -DskipTests && \
    rm -rf /var/lib/apt/lists/* 

# Runtime stage
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /build/target/etl-service-1.0-SNAPSHOT.jar /app/etl-service.jar

COPY .env /app/.env

CMD ["java", "-jar", "/app/etl-service.jar"]
 

