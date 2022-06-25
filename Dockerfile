FROM openjdk:8
VOLUME /tmp
EXPOSE 8081
COPY ./target/client-service-0.0.1-SNAPSHOT.jar client-service.jar
ENTRYPOINT ["java", "-jar", "client-service.jar"]