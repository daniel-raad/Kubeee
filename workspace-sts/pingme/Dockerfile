FROM openjdk:18-ea-11-alpine3.15 

RUN mkdir /home/java_app 

COPY ./target/pingme.jar /home/java_app/pingme.jar

EXPOSE 8090 

ENTRYPOINT java -jar /home/java_app/pingme.jar

