#!/bin/bash

# Global variables
MYSQL_IMAGE_NAME="mysql-db-mt-image"
JAVA_IMAGE_NAME="java17-api-mt-image"

NETWORK_NAME="money-transfer-docker-network"

VOLUME_NAME="mysql-mt-volume"
VOLUME_PATH=$VOLUME_NAME":/var/lib/mysql"
MYSQL_CONTAINER_NAME="mysql-db-mt-container"
JAVA_API_CONTAINER_NAME="java17-api-mt-container"

MYSQL_DATABASE_NAME="money_transfer_db"

docker stop $MYSQL_CONTAINER_NAME
docker rm $MYSQL_CONTAINER_NAME

docker stop $JAVA_API_CONTAINER_NAME
docker rm $JAVA_API_CONTAINER_NAME

docker volume rm $VOLUME_NAME

docker network rm $NETWORK_NAME

docker rmi $MYSQL_IMAGE_NAME
docker rmi $JAVA_IMAGE_NAME