FROM openjdk:11-ea-11-jre-slim

WORKDIR /discoveryserver

COPY  target/discoveryserver-0.0.1-SNAPSHOT.jar discoveryserver.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","discoveryserver.jar"]