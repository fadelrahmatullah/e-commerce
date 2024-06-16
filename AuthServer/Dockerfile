# ===============================
# Docker Image Authorization Server
# ===============================

FROM openjdk:17-alpine

# Set working directory 
WORKDIR /app/authserver

# Copy file JAR to container
COPY target/AuthServer-0.0.1-SNAPSHOT.jar /app/authserver/AuthServer-0.0.1-SNAPSHOT.jar

# Expose port 9000
EXPOSE 9000

# menjalankan jar service Authorization
CMD ["java", "-jar", "AuthServer-0.0.1-SNAPSHOT.jar"]
