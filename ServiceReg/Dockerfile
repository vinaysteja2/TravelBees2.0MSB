# Use Maven to build the application
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use OpenJDK to run the application
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/ServiceReg-0.0.1-SNAPSHOT.jar /app/ServiceReg.jar
CMD ["java", "-jar", "/app/ServiceReg.jar"]
