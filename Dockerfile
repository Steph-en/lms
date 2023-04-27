#FROM openjdk:11
#
#EXPOSE 8080
#
#ARG JAR_FILE=target/*.jar
#
#ADD ${JAR_FILE} lms-service.jar
#
#ENTRYPOINT ["java", "-jar", "lms-service.jar"]

FROM maven:3.8.7-openjdk-18-slim
#maven:amazoncorretto
#maven:3.8.7-openjdk-18-slim

WORKDIR /lms-app
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run
