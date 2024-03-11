# Use the official OpenJDK base image with JDK 17
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle ./gradle

# Copy the source code
COPY src ./src

# Build the application using Gradle
RUN ./gradlew build

# Expose the port that the application will run on
EXPOSE 8082

# Command to run the application
CMD ["java", "-jar", "build/libs/book-service-0.0.1-SNAPSHOT-plain.jar"]
