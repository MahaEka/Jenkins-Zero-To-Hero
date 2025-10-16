# Use a maintained Java 11 base image (the old AdoptOpenJDK image is removed)
FROM eclipse-temurin:11-jre-alpine

# The JAR file built by Maven
ARG artifact=target/spring-boot-web.jar

# Set working directory inside the container
WORKDIR /opt/app

# Copy the built JAR file into the image
COPY ${artifact} app.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
