![Programação-Arquitetura Java](https://github.com/jacqueline-oliveira/3698-java-clean-architecture/assets/66698429/0191ea20-432f-4583-a391-f01558004fb9)

# codechella-clean-architecture

## About (English)

**CodeChella** is an event ticket-sales simulation built with **Java 17** and **Spring Boot 3.2.2**, applying **Clean Architecture** principles: entities, value objects, use cases, and gateway ports form a domain layer fully isolated from frameworks and infrastructure. Persistence runs on PostgreSQL via Docker Compose, and unit tests cover the business rules.

> Study project developed as part of an [Alura](https://www.alura.com.br/) course; the original walkthrough below is in Portuguese.

---

O **CodeChella** é um sistema de simulação de venda de ingressos para eventos, desenvolvido em **Java 17** com **Spring Boot 3.2.2** utilizando os princípios da **Clean Architecture** (Arquitetura Limpa). O objetivo principal é isolar a lógica de domínio das tecnologias externas (banco de dados, frameworks Web), facilitando a manutenção e a testabilidade.

---

## 🔨 Objetivos do Projeto

- Conhecer os diferentes tipos de arquitetura de software.
- Praticar e aplicar os princípios da **Clean Architecture**.
- Garantir a separação de responsabilidades e o completo isolamento do domínio de negócio.
- Implementar e entender na prática: Entidades, Objetos de Valor (Value Objects), Casos de Uso (Use Cases), Gateways/Repositórios e Controladores.
- Utilizar Mappers para isolar as transferências de dados entre as camadas.

---

## 🏗️ Estrutura do Projeto (Clean Architecture)

A aplicação está estruturada nas seguintes camadas:

1. **Domain (Domínio)**:
   - Contém as regras de negócio puras e as entidades fundamentais da aplicação.
   - Não depende de nenhuma biblioteca ou framework externo.
   - [`User`](src/main/java/br/com/alura/codechella/domain/entities/user/User.java): Entidade de domínio que valida regras como formato de CPF e idade mínima (maior de 18 anos).
   - [`Address`](src/main/java/br/com/alura/codechella/domain/Address.java): Objeto de valor que representa o endereço do usuário.
   - [`UserFactory`](src/main/java/br/com/alura/codechella/domain/entities/user/UserFactory.java): Fábrica para criação facilitada e encapsulada de usuários.

2. **Application (Aplicação)**:
   - Contém os Casos de Uso que orquestram o fluxo de dados.
   - Define os contratos/gateways de comunicação com o mundo externo.
   - [`UserRegister`](src/main/java/br/com/alura/codechella/application/usecases/UserRegister.java): Caso de uso para cadastrar novos usuários.
   - [`UserUpdate`](src/main/java/br/com/alura/codechella/application/usecases/UserUpdate.java): Caso de uso para atualizar informações dos usuários.
   - [`UsersList`](src/main/java/br/com/alura/codechella/application/usecases/UsersList.java): Caso de uso para listar todos os usuários cadastrados.
   - [`UserRepository`](src/main/java/br/com/alura/codechella/application/gateways/UserRepository.java): Interface port/gateway para operações de persistência.

3. **Infrastructure (Infraestrutura)**:
   - Implementa os detalhes de infraestrutura, incluindo controladores HTTP, persistência em banco de dados relacional e mapeadores de entidade.
   - [`UserController`](src/main/java/br/com/alura/codechella/infrastructure/controller/UserController.java): Controladora REST do Spring que expõe os endpoints HTTP.
   - [`UserJpaRepositoryAdapter`](src/main/java/br/com/alura/codechella/infrastructure/gateways/UserJpaRepositoryAdapter.java): Adaptador que implementa [`UserRepository`](src/main/java/br/com/alura/codechella/application/gateways/UserRepository.java) utilizando o Spring Data JPA.
   - [`UserEntity`](src/main/java/br/com/alura/codechella/infrastructure/persistance/UserEntity.java) & [`UserJpaRepository`](src/main/java/br/com/alura/codechella/infrastructure/persistance/UserJpaRepository.java): Modelagem relacional para persistência com PostgreSQL.
   - [`UserEntityMapper`](src/main/java/br/com/alura/codechella/infrastructure/mappers/UserEntityMapper.java) & [`UserDtoMapper`](src/main/java/br/com/alura/codechella/infrastructure/controller/UserDtoMapper.java): Mappers para conversão de DTOs e entidades de banco de dados para o Domínio.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.2** (com Spring Web, Spring Data JPA e Validation)
- **PostgreSQL** (Banco de dados relacional)
- **Lombok** (Geração de boilerplate code)
- **Docker / Docker Compose** (Execução local do banco de dados)
- **JUnit 5** (Testes unitários)

---

## 🚀 Como Executar o Projeto

### 1. Pré-requisitos
- Java 17 instalado.
- Docker e Docker Compose instalados.

### 2. Inicializar o Banco de Dados
Para subir a instância do PostgreSQL configurada no projeto, execute:
```bash
docker compose up -d
```
> [!NOTE]
> As credenciais padrão do banco estão configuradas no [`docker-compose.yml`](docker-compose.yml).

### 3. Configurar Variáveis de Ambiente
A aplicação espera as seguintes variáveis para se conectar ao banco de dados (conforme o [`application.properties`](src/main/resources/application.properties)):
- `DB_HOST`: Host do banco (ex: `localhost:5432`)
- `DB_USER`: Usuário do banco (ex: `admin`)
- `DB_PASSWORD`: Senha do banco (ex: `admin`)

### 4. Executar a Aplicação
Você pode rodar a aplicação definindo as variáveis na linha de comando:
```bash
DB_HOST=localhost:5432 DB_USER=admin DB_PASSWORD=admin ./mvnw spring-boot:run
```

---

## 🔌 API Endpoints

### Usuários (`/users`)

- **POST `/users`**: Cadastra um novo usuário.
  - **Body (JSON):**
    ```json
    {
      "cpf": "123.456.789-00",
      "name": "João da Silva",
      "birthDate": "2000-01-01",
      "email": "joao@email.com"
    }
    ```
- **PUT `/users`**: Atualiza dados de um usuário existente (atualiza o email com base no CPF).
  - **Body (JSON):**
    ```json
    {
      "cpf": "123.456.789-00",
      "name": "João da Silva",
      "birthDate": "2000-01-01",
      "email": "joao.novo@email.com"
    }
    ```
- **GET `/users`**: Retorna a lista de todos os usuários cadastrados.

---

## 🧪 Testes

Os testes de unidade e regras de negócio estão localizados na pasta `src/test`.
Para rodar os testes da aplicação:
```bash
./mvnw test
```
> [!TIP]
> Os testes unitários das regras de validação do domínio podem ser encontrados em [`UserEntityTest.java`](src/test/java/br/com/alura/codechella/domain/entities/user/UserEntityTest.java).
