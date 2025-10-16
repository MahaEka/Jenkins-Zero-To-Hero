# Use a maintained Java 11 base image, forcing AMD64 platform for Mac users
FROM --platform=linux/amd64 eclipse-temurin:11-jre-alpine

# The JAR file built by Maven
ARG artifact=target/spring-boot-web.jar

# Set working directory
WORKDIR /opt/app

# Copy the built JAR file into the image
COPY ${artifact} app.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
