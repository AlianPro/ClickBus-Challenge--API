![GitHub repo size](https://img.shields.io/github/repo-size/AlianPro/Clickbus-Challenge---Place-API-REST)
![GitHub language count](https://img.shields.io/github/languages/count/AlianPro/Clickbus-Challenge---Place-API-REST)

<details id="pt">
  <summary>PT/BR:brazil:</summary>

  # Clickbus-Challenge--Place-API-REST

## :page_with_curl:Sobre
- Este é um desafio simples para testar suas habilidades na construção de APIs. Os serviços ClickBus usam principalmente tecnologias Java e Springboot. No entanto, você pode usar qualquer linguagem e framework que lhe pareça mais confortável.
```console
https://github.com/RocketBus/quero-ser-clickbus/tree/master/testes/backend-developer
```

## :bookmark_tabs:Dependências

Este projeto é construido usando:

- Java 11
- Spring Boot 2.4.3
- Spring Data
- Lombok
- MapStruct
- Swagger
- Junit 5, AssertJ, Mockito - H2 to repository tests
- MySql DataBase
- Docker - docker compose
- Prometheus
- Grafana - JVM (Micrometer)

## :earth_americas:LINUX
## :whale:Run
```console
docker-compose up
```
## :hammer:Build
```console
./mvnw clean install
./mvnw spring-boot:run
```
---
  
## :earth_americas:WINDOWS
## :whale:Run
```console
docker-compose up
```
## :hammer:Build
```console
mvnw.cmd clean install 
mvnw.cmd spring-boot:run
```
## :mag_right:Endpoints

|Método | 	Url		| 	Descrição |
|-------| ------- | ----------- |
|PUT|/click/replace/{id}| 	Editar um lugar|
|POST|/click| 	Criar um lugar|
|GET| /v2/api-docs| 	Swagger json|
|GET|/swagger-ui.html| 	Swagger html|
|GET|/click/list?name| 	Listar os lugares e filtre-os por nome|
|GET|/click/get/{id}| 	Escolher um lugar específico|
|DELETE|/click/remove/{id}| 	Deletar um lugar|

![Screenshot from 2021-02-25 21-22-58](https://user-images.githubusercontent.com/13512651/110831726-4bd78000-8279-11eb-8b25-d576ca567b4e.png)
>Swagger

![Screenshot from 2021-02-25 22-41-28](https://user-images.githubusercontent.com/13512651/110831744-4f6b0700-8279-11eb-8a01-903736b4a8ef.png)
>Grafana - JVM (Micrometer)

## :unlock:Licença 

Este software foi criado apenas para fins de estudo. Sinta-se à vontade para experimentar. 

</details>

---

# Clickbus-Challenge---Place-API-REST

## :page_with_curl:About
- This is a simple challenge to test your skills on building APIs. The ClickBus services use mainly Java and Springboot technologies. However, you can use any language and framework that you feel more confortable.
```console
https://github.com/RocketBus/quero-ser-clickbus/tree/master/testes/backend-developer
```

## :bookmark_tabs:Dependencies

This project is built using:

- Java 11
- Spring Boot 2.4.3
- Spring Data
- Lombok
- MapStruct
- Swagger
- Junit 5, AssertJ, Mockito - H2 to repository tests
- MySql DataBase
- Docker - docker compose
- Prometheus
- Grafana - JVM (Micrometer)

## :earth_americas:LINUX
## :whale:Run
```console
docker-compose up
```
## :hammer:Build
```console
./mvnw clean install
./mvnw spring-boot:run
```
---

## :earth_americas:WINDOWS
## :whale:Run
```console
docker-compose up
```
## :hammer:Build
```console
mvnw.cmd clean install 
mvnw.cmd spring-boot:run
```

## :mag_right:Endpoints

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

## :unlock:License 

This software was created for study purposes only. Feel free to try it out.

