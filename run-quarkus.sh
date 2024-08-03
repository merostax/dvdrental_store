#!/bin/bash

# Load environment variables
.  .env



DOCKERFILE_DB_PATH="./docker/db/Dockerfile"

if podman pod exists $PODNAME; then
    echo "Pod $PODNAME already exists. Skipping pod creation."
else
    podman pod create --name $PODNAME  -p 54321:54321 -p 54322:54322 -p 54323:54323
fi

wait_for_postgres() {
    until podman exec -it $DB_CONTAINER_ID psql -p 54322 -U postgres -d dvdrentalstore -c '\q' &> /dev/null; do
        echo "Waiting for PostgreSQL to be ready..."
        sleep 5
    done
}


podman build -t $STORE_CONTAINER_NAME_POSTGRES --build-arg SQL_FILE=$STORE_DB_SQL -f $DOCKERFILE_DB_PATH .
DB_CONTAINER_ID=$(podman run -d --pod  $PODNAME  $STORE_CONTAINER_NAME_POSTGRES)


mvn clean package -Pnative -f pom-quarkus.xml
 sleep 5
./target/starter-1.0-runner -Dcustomer.service.uri=http://localhost:8083/ -Dstore.service.uri=http://localhost:8082/ -Dfilm.service.uri=http://localhost:8081/ & disown
