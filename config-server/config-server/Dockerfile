
FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /configserver

COPY /target/config-server-0.0.1-SNAPSHOT.jar config-server.jar
EXPOSE 9191
ENTRYPOINT ["java","-jar","config-server.jar"]