# STC-BACKEND-ASSESSMENT

Backend Engineer assessment

## Technology Stack

* java 11
* Spring boot
* PostgreSQL
* Docker

## Installation

to run this app follow this instructions

1. Make Sure you Have Docker and Docker-Compose Installed in Your Machine
2. clone the project
3.  open terminal in project root directory
4. run this command to build the project
   > ./mvnw clean install

5. run this command to start the docker container
    > docker-compose up

## Test the app

to test the app you can use attached postman collection in project root directory called
> stc.postman_collection.json

* use basic authentication to access the secured end point
* there are tow test users you can use

1. username : userAdminEdit password : 123 has EDIT ACCESS
2. username : userAdminView password : 123 has VIEW ACCESS