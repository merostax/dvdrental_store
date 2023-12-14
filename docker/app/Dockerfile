FROM docker.io/library/eclipse-temurin:21.0.1_12-jdk-ubi9-minimal

COPY target/*.jar starter.jar
EXPOSE 8082
CMD ["java", "-jar","-Ddvdrental.jdbc.url=jdbc:postgresql://localhost:54322/dvdrentalstore", "-Djboss.bind.address=0.0.0.0", "-Djboss.bind.address.management=0.0.0.0", "-Djboss.http.port=8082", "-Djboss.management.http.port=9991", "-Dcustomer.service.uri=http://localhost:8083/", "-Dstore.service.uri=http://localhost:8082/", "-Dfilm.service.uri=http://localhost:8081/", "starter.jar"]
