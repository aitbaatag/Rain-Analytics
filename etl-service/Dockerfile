# Build
FROM eclipse-temurin:17-jdk AS builder 

WORKDIR /etl 

COPY ./pom.xml ./ 
COPY ./src ./src 


RUN apt-get update && apt-get install -y \ 
    maven && mvn clean package -DskipTests \
    && rm -rf /var/lib/apt/lists/* 

# Runtime
FROM eclipse-temurin:17-jre 

WORKDIR /etl 

COPY --from=builder /etl/target/*.jar ./etl.jar 

CMD ["java", "-jar", "etl.jar"] 

