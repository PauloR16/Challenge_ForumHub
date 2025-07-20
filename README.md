# Fórum API

Este projeto é uma API RESTful para um fórum de discussão, permitindo a criação, listagem, detalhamento, atualização e exclusão de tópicos. A segurança da API é implementada utilizando **JSON Web Tokens (JWT)** através do **Auth0**.

---

## Funcionalidades

A API oferece os seguintes endpoints para gerenciamento de tópicos:

* **`POST /topicos`**: Cria um novo tópico no fórum.
* **`GET /topicos`**: Lista todos os tópicos existentes, com suporte a paginação.
* **`GET /topicos/{id}`**: Detalha um tópico específico pelo seu ID.
* **`PUT /topicos/{id}`**: Atualiza as informações de um tópico existente.
* **`DELETE /topicos/{id}`**: Exclui um tópico do fórum (exclusão lógica).

---

### Funcionalidades de Exportação (CSV)

A API do Fórum oferece uma funcionalidade para exportar a lista completa de tópicos em formato CSV (Comma Separated Values), ideal para análises ou backup de dados.

#### Endpoint de Exportação

* **`GET /topicos/exportar/csv`**: Exporta todos os tópicos existentes para um arquivo CSV.

    **Exemplo de Uso:**

    Você pode acessar este endpoint diretamente no seu navegador ou via ferramentas como cURL, Postman, Insomnia. O download do arquivo será iniciado automaticamente.

    ```bash
    curl -H "Authorization: Bearer SEU_TOKEN_JWT_AQUI" -o topicos_export.csv http://localhost:8080/topicos/exportar/csv
    ```

    **Observações:**

    * Este endpoint requer um **token JWT válido** no cabeçalho `Authorization` para garantir que apenas usuários autenticados possam realizar a exportação.
    * O nome do arquivo CSV será gerado dinamicamente com base na data e hora da exportação (ex: `topicos_20250720_114505.csv`).
    * Para grandes volumes de dados, o processo de exportação pode levar alguns instantes.

---

## Segurança

A segurança desta API é gerenciada pelo **Auth0**, utilizando tokens JWT para autenticação e autorização. Todas as requisições que modificam recursos (POST, PUT, DELETE) e o acesso a recursos específicos (GET por ID, GET para exportação) exigem um token JWT válido no cabeçalho `Authorization` (Bearer Token).

### Como obter um Token JWT

Para interagir com os endpoints protegidos, você precisará de um token JWT válido, emitido pelo Auth0. O processo geralmente envolve:

1.  **Registro/Login**: Ter uma conta configurada no Auth0.
2.  **Obtenção do Token**: Utilizar as credenciais da sua aplicação ou de um usuário para solicitar um token JWT ao Auth0. Isso normalmente é feito através de um fluxo OAuth 2.0 (e.g., Client Credentials Grant, Authorization Code Grant, etc.), dependendo do tipo de cliente.

### Exemplo de Requisição Autorizada

```http
POST /topicos
Authorization: Bearer SEU_TOKEN_JWT_AQUI
Content-Type: application/json

{
    "titulo": "Novo Tópico de Exemplo",
    "mensagem": "Esta é uma mensagem de teste para o novo tópico.",
    "autor": "Usuário Teste",
    "curso": "Desenvolvimento Web"
}

Tecnologias Utilizadas
° Spring Boot: Framework para o desenvolvimento rápido da aplicação Java.

° Spring Web: Criação de aplicações web e RESTful.

° Spring Data JPA: Para persistência de dados e interação com o banco de dados.

° Spring Security: Framework para autenticação e autorização.

° Lombok: Simplifica o desenvolvimento Java com anotações para gerar código boilerplate.

° Spring Boot DevTools: Ferramentas para melhorar a produtividade durante o desenvolvimento.

° Flyway Migration: Gerenciamento de versionamento de banco de dados.

° MySQL Driver: Conectividade com bancos de dados MySQL.

° Validation: Validação de dados de entrada.

° Auth0: Plataforma de identidade para autenticação e autorização com JWT.

° Maven: Ferramenta de gerenciamento de dependências.

Como Rodar o Projeto
1. Clonar o Repositório:

git clone [URL_DO_SEU_REPOSITORIO]
cd nome-do-projeto

2. Configurar o Auth0:

Crie uma conta no Auth0 e configure uma aplicação ou API.

Obtenha suas credenciais (Domain, Audience, Client ID, Client Secret, etc.).

Configure as variáveis de ambiente ou o arquivo application.properties/application.yml do Spring Boot com as credenciais do Auth0.

3. Configurar o Banco de Dados.

Endpoints da API
Abaixo estão os detalhes de cada endpoint:

Criar Tópico
POST /topicos

Cria um novo tópico. Requer autenticação.

Corpo da Requisição (JSON):

{
    "titulo": "string",
    "mensagem": "string",
    "autor": "string",
    "curso": "string"
}

Respostas:

° 201 Created: Tópico criado com sucesso. Retorna os detalhes do tópico e o cabeçalho Location com a URI do novo recurso.

° 400 Bad Request: Dados inválidos ou tópico com mesmo título/mensagem já existe.

° 401 Unauthorized: Token JWT ausente ou inválido.

° 403 Forbidden: Token JWT válido.

Listar Tópicos
GET /topicos

Lista todos os tópicos com paginação.

Parâmetros de Query (Opcionais):

° page: Número da página (padrão: 0).

° size: Quantidade de itens por página (padrão: 10).

° sort: Campo para ordenação (ex: sort=dataCriacao,desc).

Respostas:

200 OK: Retorna uma lista paginada de tópicos.

Detalhar Tópico
GET /topicos/{id}

Retorna os detalhes de um tópico específico. Requer autenticação.

Parâmetros de Path:

° id: ID do tópico (Long).

Respostas:

° 200 OK: Retorna os detalhes do tópico.

° 404 Not Found: Tópico não encontrado.

° 401 Unauthorized: Token JWT ausente ou inválido.

° 403 Forbidden: Token JWT válido, mas sem permissão.

Atualizar Tópico
PUT /topicos/{id}

Atualiza as informações de um tópico existente. Requer autenticação.

Parâmetro de Path:

° id: ID do tópico a ser atualizado (Long).

Corpo da Requisição (JSON):

{
    "titulo": "string",
    "mensagem": "string",
    "autor": "string",
    "curso": "string"
}

Respostas:

° 200 OK: Tópico atualizado com sucesso. Retorna os detalhes do tópico.

° 400 Bad Request: Dados inválidos ou ID na URL não corresponde ao ID no corpo.

° 404 Not Found: Tópico não encontrado.

° 401 Unauthorized: Token JWT ausente ou inválido.

° 403 Forbidden: Token JWT válido, mas sem permissão.

Excluir Tópico
DELETE /topicos/{id}

Exclui um tópico (exclusão lógica). Requer autenticação.

Parâmetro de Path:

° id: ID do tópico a ser excluído (Long).

Respostas:

° 204 No Content: Tópico excluído com sucesso.

° 404 Not Found: Tópico não encontrado.

° 401 Unauthorized: Token JWT ausente ou inválido.

° 403 Forbidden: Token JWT válido, mas sem permissão.
