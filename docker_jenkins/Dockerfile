

FROM jenkins/jenkins:lts
USER root 
RUN apt-get update && apt-get install -y maven
RUN apt-get install --reinstall -y procps

USER jenkins