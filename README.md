# Language-Service

This service is one of the backend services used for a showcase for a microservice architecture.
The other services are [country-service](https://github.com/bettercodepaul/microservice-country-service) and [currency-service](https://github.com/bettercodepaul/microservice-currency-service). The showcase is part of a guest lecture at the University Stuttgart. The documents to the lecture can be found [here](https://github.com/bettercodepaul/microservices-kubernetes-docs).

This project uses [Quarkus](https://quarkus.io/), the Supersonic Subatomic Java Framework.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
mvn package quarkus:dev -Ddebug=5006
```

## Packaging and running the application

The application is packageable using `mvn package`.
It produces the executable `language-service-1.0.0-SNAPSHOT-runner.jar` file in `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/language-service-1.0.0-SNAPSHOT-runner.jar`.

## Creating a docker image
The application can be provided as docker image by building the image with `docker build --no-cache -t exxcellent/cps-language-service .`

The application can then be started with the command `docker run -i --rm -p 8082:8082 exxcellent/cps-language-service` and is available on `localhost:8082`.
