FROM amazoncorretto:22-alpine-jdk
WORKDIR /backend-app
COPY target/backend-0.0.1-SNAPSHOT.jar /backend-app/backend-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/backend-app/backend-0.0.1-SNAPSHOT.jar"]
