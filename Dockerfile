FROM maven:3-openjdk-18 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:18-jdk-slim-buster
COPY --from=build /target/ToDoApp-0.0.1-SANPSHOT.jar ToDoApp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","ToDoApp.jar"]