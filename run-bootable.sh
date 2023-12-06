#!/bin/bash
##README lesen
export POSTGRESQL_SERVICE_HOST=localhost
export POSTGRESQL_SERVICE_PORT=54322
export POSTGRESQL_USER=postgres
export POSTGRESQL_PASSWORD=trust


mvn clean package
sleep 5
java -jar -Djboss.http.port=8082 -Djboss.management.http.port=9991 target/starter-bootable.jar

