# Clickbus-Challenge---Place-API-REST

## Dependencies

This project is built using:

- Java 11
- Spring Boot 2.4.3
- Spring Data
- Lombok
- MapStruct
- Swagger
- Junit 5 - H2 to repository tests
- MySql DataBase
- Docker - docker compose
- Prometheus
- Grafana - JVM (Micrometer)

## Build

```console
./mvnw clean install
```

## Run
```console
docker-compose up
```

## Endpoints

|Method | 	Url		| 	Description |
|-------| ------- | ----------- |
|GET| /v2/api-docs| 	Swagger json|
|GET|/swagger-ui.html| 	Swagger html|
|GET|/click/list?name| 	List places and filter them by name|
|GET|/click/get/{id}| 	Get a specific place|
|POST|/click| 	Create a place|
|PUT|/click/replace/{id}| 	Edit a place|
|DELETE|/click/remove/{id}| 	Delete a place|

## License 

This is a simple challenge to test your skills on building APIs. The ClickBus services use mainly Java and Springboot technologies. However, you can use any language and framework that you feel more confortable.https://github.com/RocketBus/quero-ser-clickbus/tree/master/testes/backend-developer

This software was created for study purposes only. Feel free to try it out.
