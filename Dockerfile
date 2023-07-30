FROM openjdk:17 as build
ENV SAROS_DB_USERNAME ${SAROS_DB_USERNAME}
ENV SAROS_DB_PASSWORD ${SAROS_DB_PASSWORD}
COPY target/saros-api-monolith-0.0.1-SNAPSHOT.jar saros-api-1.0.0.jar
ENTRYPOINT ["java","-jar","/saros-api-1.0.0.jar"]