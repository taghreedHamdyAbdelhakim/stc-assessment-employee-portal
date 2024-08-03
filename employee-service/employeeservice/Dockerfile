FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /employeeservice

COPY target/employeeservice-0.0.1-SNAPSHOT.jar employeeservice.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","employeeservice.jar"]