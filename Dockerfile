FROM anapsix/alpine-java:8_server-jre
ADD sbd-accept-transactional/target/sbd-accept-transactional-1.0.0.jar /app.jar
EXPOSE 3000
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


