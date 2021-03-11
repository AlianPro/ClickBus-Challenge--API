# Clickbus-Challenge---Place-API-REST

## About
- This is a simple challenge to test your skills on building APIs. The ClickBus services use mainly Java and Springboot technologies. However, you can use any language and framework that you feel more confortable.
```console
https://github.com/RocketBus/quero-ser-clickbus/tree/master/testes/backend-developer
```

## Dependencies

This project is built using:

- Java 11
- Spring Boot 2.4.3
- Spring Data
- Lombok
- MapStruct
- Swagger
- Junit 5, Mockito - H2 to repository tests
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
|PUT|/click/replace/{id}| 	Edit a place|
|POST|/click| 	Create a place|
|GET| /v2/api-docs| 	Swagger json|
|GET|/swagger-ui.html| 	Swagger html|
|GET|/click/list?name| 	List places and filter them by name|
|GET|/click/get/{id}| 	Get a specific place|
|DELETE|/click/remove/{id}| 	Delete a place|

![Screenshot from 2021-02-25 21-22-58](https://user-images.githubusercontent.com/13512651/110831726-4bd78000-8279-11eb-8b25-d576ca567b4e.png)
>Swagger

![Screenshot from 2021-02-25 22-41-28](https://user-images.githubusercontent.com/13512651/110831744-4f6b0700-8279-11eb-8a01-903736b4a8ef.png)
>Grafana - JVM (Micrometer)

## License 

This software was created for study purposes only. Feel free to try it out.

