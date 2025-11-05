🏴‍☠️ One Piece API – Spring Boot 3 + Java 21 + PostgreSQL

API REST para gerenciar Piratas e suas Missões no Novo Mundo.
Projeto didático com arquitetura de mercado: Controllers → Services → Repositories → Entities → DTOs → Mappers, validações, exceções globais e documentação automática com Swagger.

✅ Stack

Java 21

Spring Boot 3.5.x

Spring Data JPA (Hibernate)

PostgreSQL

Validation (Jakarta)

Lombok

Springdoc OpenAPI (Swagger)

📦 Requisitos

JDK 21

Maven 3.9+

PostgreSQL >= 14 (rodando localmente)

🗄️ Banco de Dados

Crie o banco local (ajuste o nome se quiser):

CREATE DATABASE api_onepiece;

application.properties (commitável)
spring.application.name=OnePiece
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
spring.profiles.include=local
# dialeto é detectado automaticamente no Boot 3.5, não precisa setar

application-local.properties (NÃO commitar)

coloque em src/main/resources/application-local.properties e adicione no .gitignore:

# application-local*
src/main/resources/application-local.properties


Conteúdo (ajuste usuário/senha/porta):

spring.datasource.url=jdbc:postgresql://localhost:5432/api_onepiece
spring.datasource.username=SEU_USER
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=org.postgresql.Driver

▶️ Como rodar

Via IntelliJ (botão verde) ou via Maven:

mvn spring-boot:run


Se subir ok, você verá algo como:

Tomcat started on port 8080
Started OnePieceApplication...

📖 Swagger (documentação interativa)

UI: http://localhost:8080/swagger-ui.html
(ou /swagger-ui/index.html)

OpenAPI JSON: http://localhost:8080/v3/api-docs

Você consegue testar todos os endpoints direto no navegador (Try it out).

🧱 Modelagem (resumo)

Pirata

id (UUID)

nome (String)

raca (enum: HUMANO, CIBORGUE, MINK, ANAO, TRITAO)

tripulacao (String)

status (enum: ATIVO, CAPTURADO)

missoes (OneToMany)

Missao

id (UUID)

danger (enum: D, C, B, A, S)

tipo (enum: EXPLORACAO, BATALHA_NAVAL, SAQUE)

status (enum: CONCLUIDA, EM_ANDAMENTO)

pirata (ManyToOne — obrigatório)

🧭 Endpoints
PIRATAS
Criar pirata

POST /piratas

{
  "nome": "Roronoa Zoro",
  "raca": "HUMANO",
  "tripulacao": "Chapeus de Palha",
  "status": "ATIVO"
}


201 Created

{
  "id": "8f2b60c0-1f47-11ef-8b6d-0242ac110002",
  "nome": "Roronoa Zoro",
  "raca": "HUMANO",
  "tripulacao": "Chapeus de Palha",
  "status": "ATIVO"
}

Atualizar pirata

PUT /piratas/{id}

{
  "nome": "Roronoa Zoro",
  "raca": "HUMANO",
  "tripulacao": "Chapeus de Palha",
  "status": "ATIVO"
}


200 OK → mesmo formato do DTO acima.

Buscar por ID (resumo)

GET /piratas/{id}
200 OK

{
  "id": "8f2b60c0-1f47-11ef-8b6d-0242ac110002",
  "nome": "Roronoa Zoro",
  "raca": "HUMANO",
  "tripulacao": "Chapeus de Palha",
  "status": "ATIVO"
}

Buscar por ID (detalhe com missões)

GET /piratas/{id}/detalhe
200 OK

{
  "id": "8f2b60c0-1f47-11ef-8b6d-0242ac110002",
  "nome": "Roronoa Zoro",
  "raca": "HUMANO",
  "tripulacao": "Chapeus de Palha",
  "status": "ATIVO",
  "missoes": [
    {
      "id": "5d185852-b0b7-420e-9529-f832474139c4",
      "danger": "A",
      "tipo": "BATALHA_NAVAL",
      "status": "EM_ANDAMENTO"
    }
  ]
}

Buscar por raça

GET /piratas/raca/{raca}
Ex.: /piratas/raca/HUMANO
200 OK – lista de PirataDTO.

Listar todos

GET /piratas
200 OK – lista de PirataDTO.

Deletar

DELETE /piratas/{id}
204 No Content

MISSÕES
Criar missão (sempre ligada a um pirata)

POST /missoes

{
  "danger": "A",
  "tipo": "BATALHA_NAVAL",
  "status": "EM_ANDAMENTO",
  "pirataId": "8f2b60c0-1f47-11ef-8b6d-0242ac110002"
}


201 Created

{
  "id": "5d185852-b0b7-420e-9529-f832474139c4",
  "danger": "A",
  "tipo": "BATALHA_NAVAL",
  "status": "EM_ANDAMENTO",
  "pirataId": "8f2b60c0-1f47-11ef-8b6d-0242ac110002"
}

Atualizar missão

PUT /missoes/{id}

{
  "danger": "S",
  "tipo": "EXPLORACAO",
  "status": "CONCLUIDA",
  "pirataId": "8f2b60c0-1f47-11ef-8b6d-0242ac110002"
}

Buscar missão por ID

GET /missoes/{id} → MissaoDTO

Listar todas as missões

GET /missoes → lista de MissaoDTO

Filtros especiais

GET /missoes/danger/{nivel} (ex.: /missoes/danger/A)

GET /missoes/status/{status} (ex.: /missoes/status/EM_ANDAMENTO)

❗ Modelo de erros (tratamento global)

A API trata exceções globalmente e retorna JSON padronizado.
Exemplos:

404 – recurso não encontrado

{
  "timestamp": "2025-11-04T22:58:12Z",
  "error": "NOT_FOUND",
  "message": "Pirata não encontrado"
}


400 – validação

{
  "timestamp": "2025-11-04T22:41:01Z",
  "error": "VALIDATION_ERROR",
  "message": "Campos inválidos",
  "fields": {
    "nome": "must not be blank",
    "tripulacao": "must not be blank"
  }
}

🔒 Validações (regras da Marinha)

Nenhum Pirata pode ser criado sem nome, raca, tripulacao, status.

Nenhuma Missão pode ser criada sem danger, tipo, status, e pirataId.

Toda missão pertence a um único pirata.

Ao consultar um pirata no endpoint de detalhe, suas missões são exibidas.

🧰 Dicas (ambiente profissional)

Perfis: use application-local.properties para credenciais locais (não commitar).

EntityGraph para evitar N+1 (findWithMissoesById).

DTOs + Mappers para isolar entidade do payload da API.

Exceptions customizadas (ResourceNotFoundException, BusinessException) + @RestControllerAdvice.
