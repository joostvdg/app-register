FROM openjdk:8-jdk-slim

## LABELS
LABEL authors="Joost van der Griendt <joostvdg@gmail.com>"
LABEL version="0.1.0"
LABEL description="App Register"
## LABELS

ENV TINI_VERSION v0.16.1
ADD https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini /tini
RUN chmod +x /tini
ENTRYPOINT ["/tini", "-vv","-g", "--"]
ENV DATE_CHANGED="20180120-1525"
COPY build/libs/app-register-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java", "-Xms256M","-Xmx480M", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=swarm", "-jar", "/app.jar"]