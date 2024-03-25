FROM openjdk:17-jdk-alpine
COPY build/libs/services-1.0.1-SNAPSHOT.jar app-1.0.1.jar
ENTRYPOINT ["java","-jar","/app-1.0.1.jar"]