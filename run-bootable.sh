#!/usr/bin/bash
source .env

DOCKERFILE_DB_PATH="./docker/db/Dockerfile"
DOCKERFILE_PATH="./docker/app/Dockerfile"
mvn clean  package
wait $!

if podman pod exists $PODNAME; then
    echo "Pod $PODNAME already exists. Skipping pod creation."
else
    podman pod create --name $PODNAME -p 54321:54321 -p 54322:54322 -p 54323:54323   -p 8083:8083 -p 8082:8082 -p 8081:8081
fi
wait_for_postgres() {
    until podman exec -it $DB_CONTAINER_ID psql -p 54322 -U postgres -d dvdrentalstore -c '\q' &> /dev/null; do
        echo "Waiting for PostgreSQL to be ready..."
        sleep 5
    done
}
 podman build -t  $STORE_CONTAINER_NAME_POSTGRES --build-arg SQL_FILE=$STORE_DB_SQL -f $DOCKERFILE_DB_PATH .
DB_CONTAINER_ID=$(podman run -d --pod  $PODNAME  $STORE_CONTAINER_NAME_POSTGRES)

wait_for_postgres

 podman build -t $STORE_CONTAINER_NAME -f $DOCKERFILE_PATH .
podman run  -d  --pod $PODNAME    -e POSTGRESQL_USER=postgres  -e POSTGRESQL_PASSWORD=postgres $STORE_CONTAINER_NAME
