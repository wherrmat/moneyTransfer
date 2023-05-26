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

DB_URL="jdbc:mysql://"$MYSQL_CONTAINER_NAME":3306/"$MYSQL_DATABASE_NAME
DB_USER="root"
DB_PASSWORD="Malta_2023"

# Create the image mysql-db-mt-image
cd mysql_db
docker build -t $MYSQL_IMAGE_NAME .
echo "MySQL image has been created"
echo

# Create the image java17-api-mt-image
cd ..
docker build -t $JAVA_IMAGE_NAME .
echo "Java image has been created"
echo

# Create the network
docker network create $NETWORK_NAME
echo "Docker network has been created"
echo

# Create and execute the mysql container
docker run --name $MYSQL_CONTAINER_NAME --network $NETWORK_NAME -e MYSQL_ROOT_PASSWORD=$DB_PASSWORD -v $VOLUME_PATH -p 3306:3306 -d $MYSQL_IMAGE_NAME
echo "MySQL container has been deployed"
echo

# Create and execute the java container
docker run --name $JAVA_API_CONTAINER_NAME --network $NETWORK_NAME -p 8080:8080 -e "DATABASE_URL=$DB_URL" -e "DATABASE_USERNAME=$DB_USER" -e "DATABASE_PASSWORD=$DB_PASSWORD" -d $JAVA_IMAGE_NAME
echo "Java container has been deployed"
echo

# Wait for 15 seconds
echo "Wait for the containers start correctly"
sleep 15

docker ps

echo "Wait for the API deployment"
docker logs -f $JAVA_API_CONTAINER_NAME &
sleep 15
echo