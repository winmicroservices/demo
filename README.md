# demo

## Description 

A very simple Springboot app.  We'll use this to kick off the winmicroservices
organization set of repositories.

## GraalVM

GraalVM builds a native image for the Java app to start up and run faster.

### Build GraalVM Docker Image with Maven

```
mvn -Pnative spring-boot:build-image
```