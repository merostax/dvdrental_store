#!/bin/bash
##README lesen
mvn clean package
sleep 5
java -jar -Ddvdrental.jdbc.url=jdbc:postgresql://localhost:5432/dvdrentalstore -Dcustomer.service.uri=http://localhost:8083/ -Dstore.service.uri=http://localhost:8082/ -Dfilm.service.uri=http://localhost:8081/ -Djboss.http.port=8082 -Djboss.management.http.port=9991 target/starter-bootable.jar

