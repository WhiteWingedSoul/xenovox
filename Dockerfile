# Use the official JDK image as the parent image
FROM openjdk:8-jdk-alpine

# Set the working directory to /app inside the container
WORKDIR /app

# Run ./gradlew run when container launches
CMD ["./gradlew", "clean", "bootRun"]