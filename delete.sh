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
echo "MySQL container has been deleted"

docker stop $JAVA_API_CONTAINER_NAME
docker rm $JAVA_API_CONTAINER_NAME
echo "Java container has been deleted"

docker volume rm $VOLUME_NAME
echo "Volume has been deleted"

docker network rm $NETWORK_NAME
echo "Network has been deleted"

docker rmi $MYSQL_IMAGE_NAME
docker rmi $JAVA_IMAGE_NAME
echo "Images have been deleted"