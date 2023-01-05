# demo

## Description 

A very simple Springboot app.  We'll use this to kick off the winmicroservices
organization set of repositories.

## GraalVM

GraalVM builds a native image for the Java app to start up and run faster.

### Springboot support

Springboot supports GraalVM.  Refer to this link on [SpringBoot GraalVM](https://docs.spring.io/spring-boot/docs/3.0.0/reference/html/native-image.html#native-image) support.

### Build GraalVM Docker Image with Maven

```
mvn -Pnative spring-boot:build-image
```

## APIs

### Create Employee

This is a sample http POST for creating an employee.

```
curl -X POST http://localhost:8080/v1/api/employee/create \
   -H 'Content-Type: application/json' \
   -d '{"name":"Bill Polinchak","city":"Venice"}'
```

### Get Employee

This is a sample http GET for retreiving an employee.

```
curl http://localhost:8080/v1/api/employee/1
```