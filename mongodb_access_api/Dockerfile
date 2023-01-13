FROM openjdk:18-ea-11-alpine3.15 

RUN mkdir /home/mongo

COPY ./target/mongodbtest.jar /home/mongo/mongodbtest.jar

ENV MONGODB_ENDPOINT=172.19.0.2:27017


ENTRYPOINT java -jar /home/mongo/mongodbtest.jar

