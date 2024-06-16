# ===============================
# Docker Image Inventory
# ===============================

FROM openjdk:17-alpine

# Set working directory di dalam container
WORKDIR /app/inventory

# Copy file JAR hasil build dari Service A ke dalam container
COPY target/Order-0.0.1-SNAPSHOT.jar /app/inventory/Order-0.0.1-SNAPSHOT.jar
COPY local-maven-repo/com/app/e_commerce/Source/Source-0.0.1-SNAPSHOT.jar /app/inventory/local-maven-repo/Source-0.0.1-SNAPSHOT.jar

# Expose port 9002
EXPOSE 9002

# menjalankan jar service Order
CMD ["java", "-jar", "Order-0.0.1-SNAPSHOT.jar"]
