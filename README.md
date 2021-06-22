# Altir Test Project
Micro-service for creating order for appropriate item. When client order, nearest warehouse will find to deliver item as soon as possible.

## Env
You need to install Docker, Gradle and Java 11 on your local environment. After that, go to root application directory and run command:

`$ docker-compose -f docker/docker-compose-local.yml up`

## Run local
You can start this service as a Spring Boot application.

## Endpoints
###1. Create order endpoint:

`POST localhost:8080/orders`

body (JSON):

`{
    "clientId": 1,
    "itemId": 1
}`

###2. Get order endpoint:

`GET localhost:8080/orders/1`
