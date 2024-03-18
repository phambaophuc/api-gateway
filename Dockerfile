FROM maven:3.8.4-openjdk-17 AS build

COPY . /app
WORKDIR /app

RUN mvn clean package

FROM openjdk:17-jdk-slim

EXPOSE 8761

COPY --from=build /app/target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
