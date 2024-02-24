# Use the official JDK 21 base image
FROM amazoncorretto:21.0.2-al2023

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY build/libs/posttest-0.0.1-SNAPSHOT.jar /app/posttest.jar


# Run the JAR file when the container launches
CMD ["java", "-jar", "posttest.jar"]

# FROM maven:3.8.5-openjdk-17

# WORKDIR /bezkoder-app
# COPY . .
# RUN mvn clean install

# CMD mvn spring-boot:run
