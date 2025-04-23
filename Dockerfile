FROM openjdk:17-jdk-slim
VOLUME /tmp
CCOPY target/*.jar dwarka.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080

