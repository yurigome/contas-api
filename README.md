Contas API

API REST desenvolvida em Java + Spring Boot para gerenciamento de contas, com integração a um serviço externo de usuários (API de Usuários) para enriquecer os dados retornados de cada conta.

Tecnologias
Java
Spring Boot
Spring Data JPA
PostgreSQL
REST APIs
Integração com API externa (Usuário)
Funcionalidades
Cadastro de novas contas
Listagem de todas as contas
Atualização de contas existentes
Remoção de contas
Busca automática dos dados do usuário dono da conta em uma API externa, retornando essas informações junto com os dados da conta
Como executar
Pré-requisitos
Java 17+
PostgreSQL configurado e rodando localmente
Maven (ou usar o Maven Wrapper incluso no projeto)
Passos
bash
# Clone o repositório
git clone https://github.com/yurigome/contas-api.git

# Entre na pasta do projeto
cd contas-api/ContasApi-1

# Configure a conexão com o banco de dados em:
# src/main/resources/application.properties

# Execute o projeto
./mvnw spring-boot:run

A aplicação estará disponível em http://localhost:8080 (porta padrão do Spring Boot, ajuste caso tenha configurado outra).

Estrutura do projeto
src/main/java/br/com/yuri/
├── config/         # Configurações da aplicação
├── controllers/     # Endpoints REST
├── dtos/             # Objetos de transferência de dados (DTOs)
├── entities/         # Entidades JPA
├── repositories/     # Repositórios de acesso a dados
└── services/         # Regras de negócio e integração com API externa
Autor

Yuri Gomes — LinkedIn | GitHub
