# Godric

This REST application was created to manage Accounts and Transactions.

### Account Endpoints:
- To create an account: POST `/accounts`
- To find an account by its ID: GET `/accounts/{id}`

### Transaction Endpoint:
- To create a transaction: POST `/transactions`

## Technologies
- Java 11
- Maven
- Spring Boot
- Docker
- Docker-Compose
- Postgres
- Flyway
- Swagger

## Getting Started
```SHELL
> git clone https://github.com/vladf2n/godric.git
> docker-compose up
```
- When the project is up you can see the endpoints documented here: http://localhost:8080/swagger-ui/index.html

## Running Tests
```SHELL
> mvn test
```