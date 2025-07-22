# 📘 Fórum API

Uma API RESTful para gerenciamento de tópicos em um fórum de discussão. Permite **criar**, **listar**, **detalhar**, **atualizar** e **excluir** tópicos, com segurança via **JWT** utilizando **Auth0**, além de funcionalidade para **exportação em CSV**.

---

## ⚙️ Funcionalidades

- `POST /topicos`: Cria um novo tópico.
- `GET /topicos`: Lista todos os tópicos (com paginação).
- `GET /topicos/{id}`: Detalha um tópico específico.
- `PUT /topicos/{id}`: Atualiza um tópico.
- `DELETE /topicos/{id}`: Exclusão lógica de um tópico.


**Exemplo com URL:**

```bash
curl -H "Authorization: Bearer SEU_TOKEN_JWT_AQUI" \
     -o topicos_export.csv \
     http://localhost:8080/topicos/exportar/csv

🔒 Este endpoint exige autenticação via Bearer Token JWT.

🔐 Segurança com JWT (Auth0)
Todos os endpoints que alteram ou acessam dados sensíveis exigem token JWT válido:

Cabeçalho: Authorization: Bearer SEU_TOKEN_JWT_AQUI

Como obter um token JWT:
Crie uma conta e configure uma aplicação no Auth0.

Obtenha credenciais: Client ID, Client Secret, Domain, Audience.

Utilize o fluxo OAuth adequado (ex: Client Credentials) para gerar o token.

🛠️ Tecnologias Utilizadas
Spring Boot – framework principal

Spring Web – criação de endpoints REST

Spring Data JPA – persistência com JPA

Spring Security – autenticação e autorização

Lombok – redução de boilerplate

Flyway – versionamento de banco de dados

MySQL – banco de dados relacional

Validation – validação de dados de entrada

Auth0 – autenticação com JWT

DevTools – ferramentas de produtividade

Maven – gerenciamento de dependências

▶️ Como Executar
1. Clone o repositório

git clone https://github.com/seu-usuario/forum-api.git
cd forum-api

2. Configure o Auth0
Crie uma aplicação em https://auth0.com/

Obtenha: Domain, Audience, Client ID, Client Secret

Configure no application.properties:
auth0.audience=SEU_AUDIENCE
auth0.issuer=https://SEU_DOMINIO.auth0.com/

3. Configure o banco de dados (MySQL)

spring.datasource.url=jdbc:mysql://localhost:3306/forumdb
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=validate

Certifique-se de que o Flyway aplicará as migrações automaticamente.

4. Compile e execute

mvn clean install
mvn spring-boot:run

Acesse: http://localhost:8080

📚 Endpoints Detalhados
🔸 Criar Tópico

POST /topicos
Authorization: Bearer <token>
Content-Type: application/json

{
  "titulo": "string",
  "mensagem": "string",
  "autor": "string",
  "curso": "string"
}

🔸 Listar Tópicos

GET /topicos

Parâmetros opcionais:
page, size, sort

🔸 Detalhar Tópico

GET /topicos/{id}
Authorization: Bearer <token>

🔸 Atualizar Tópico

PUT /topicos/{id}
Authorization: Bearer <token>
Content-Type: application/json

🔸 Excluir Tópico

DELETE /topicos/{id}
Authorization: Bearer <token>

✅ Respostas HTTP
Código	Significado
200	OK
201	Criado
204	Sem Conteúdo (exclusão)
400	Requisição inválida
401	Não autenticado (token ausente)
403	Sem permissão (token inválido)
404	Recurso não encontrado

📄 Licença
Este projeto está sob a licença MIT.

✉️ Contato
Em caso de dúvidas ou sugestões, entre em contato:

Autor: Paulo

