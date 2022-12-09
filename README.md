# demo

## Description 

A very simple Springboot app.  We'll use this to kick off the winmicroservices
organization set of repositories.

## GraalVM

GraalVM builds a native image for the Java app to start up and run faster.

### Springboot support

Springboot supports GrallVM.  Refer to this link on [SpringBoot GrallVM](https://docs.spring.io/spring-boot/docs/3.0.0/reference/html/native-image.html#native-image) support.

### Build GraalVM Docker Image with Maven

```
mvn -Pnative spring-boot:build-image
```
