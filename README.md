# teste-pratico-deliver-it
## 💻 Projeto
API para listagem de débitos desenvolvido para o teste prático da Deliver IT.

## :hammer_and_wrench: Features
-   [ ] Cadastro de contas
-   [ ] Busca de contas
-   [ ] Exclusão de contas
-   [ ] Atualização de contas para constarem como quitadas

## ✨ Tecnologias
-   [ ] Spring
-   [ ] Spring Boot
-   [ ] JPA
-   [ ] Maven
-   [ ] Flyway
-   [ ] Docker
-   [ ] PostgreSQL

## 🚀 Etapas para o deploy
1.  Instale as dependências do projeto:
```bash
mvn clean install
```

2. Execute o Docker Compose
```bash
docker-compose up -d
```

Agora é só executar o projeto e testar as APIs! 
A aplicação estará disponível em http://localhost:8080/api/v1/conta.

## 🧪 Exemplos de testes
### POST
URL =  http://localhost:8080/api/v1/conta
```json
{
  "nome": "Claro",
  "valorOriginal": "100.50",
  "dataVencimento": "2025-08-25"
}
```

### GET
URL =   http://localhost:8080/api/v1/conta

### DELETE
URL =  http://localhost:8080/api/v1/conta/*[ID]*

### PUT
URL =  http://localhost:8080/api/v1/conta/*[ID]*
