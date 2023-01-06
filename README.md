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

### Hatoes

The rest apis in this example app follow the Hatoes link strategy.
This [article](https://www.springcloud.io/post/2022-04/hateoas-spring-boot-and-jpa/#gsc.tab=0) has a description of how Hatoes works with SpringBoot.

### Create Customer

This is a sample http POST for creating a customer.

```
curl -X POST http://localhost:8080/api/v1/customer/create \
   -H 'Content-Type: application/json' \
   -d '{"firstName":"Bill","lastName":"Polinchak","city":"Venice"}'
```

### Get Customer

This is a sample http GET for retreiving a customer.

```
curl http://localhost:8080/api/v1/customer/1
```

### Get Customers

This is a sample http GET for retreving a list of customers.

```
curl http://localhost:8080/api/v4/customers?page=0&size=10
```
