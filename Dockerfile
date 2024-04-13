FROM openjdk:17-jdk-alpine
COPY build/libs/services-1.1.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]