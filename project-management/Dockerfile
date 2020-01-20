FROM ubuntu:latest

MAINTAINER Will R "test@email.com"

RUN apt-get update && apt-get install -y openjdk-11-jdk

ENV version=aws-db-usage

ENV dbuser=postgres
ENV dbpass=password321
ENV jdbcurl=jdbc:postgresql://pmadatabaseaws.cjroheyvfz7d.us-east-2.rds.amazonaws.com:5432/demo_pma_db


WORKDIR /usr/local/bin/

ADD target/pma-app.jar .

# CMD ["/bin/bash"]

ENTRYPOINT ["java", "-jar", "pma-app.jar"]