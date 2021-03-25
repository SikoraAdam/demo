FROM maven:3.6.3-openjdk-11-slim AS build
COPY src /Users/AtendeMedica/.jenkins/repo/demo/src
COPY pom.xml /Users/AtendeMedica/.jenkins/repo/demo
RUN mvn -f /Users/AtendeMedica/.jenkins/repo/demo/pom.xml clean package

FROM openjdk:11-slim
COPY --from=build /Users/AtendeMedica/.jenkins/repo/demo/target/*.jar /Users/AtendeMedica/.jenkins/repo/demo/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/Users/AtendeMedica/.jenkins/repo/demo/demo.jar"]

#docker build . -t xds:v1
#docker run --net=host xds:v1