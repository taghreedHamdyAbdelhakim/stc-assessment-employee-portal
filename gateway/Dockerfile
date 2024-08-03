FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /gateway

COPY target/gateway-0.0.1-SNAPSHOT.jar gateway.jar
EXPOSE 8600

ENTRYPOINT ["java","-jar","gateway.jar"]