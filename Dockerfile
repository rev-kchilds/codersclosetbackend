FROM openjdk:11-jre-slim

COPY target/e-commerce-0.0.1-SNAPSHOT.jar /home/app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]