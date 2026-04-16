#  API de Registro de Veículos

REST API desenvolvida como solução para um desafio técnico de Back-end Jr.

## 📋 Sobre o projeto

API para cadastro de veículos que integra com uma fonte externa para enriquecer os dados. Ao cadastrar um veículo informando apenas o proprietário, CPF e placa, o sistema consulta automaticamente uma API externa para obter marca, modelo, chassi e status de licenciamento.

## 🛠️ Tecnologias utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database** (banco em memória)
- **Lombok**
- **Bean Validation** (jakarta.validation + hibernate-validator)
- **RestTemplate** (consumo de API externa)
- **Maven**

## 📁 Estrutura do projeto

```
src/main/java/desafio/backend/autoscore/
├── client/         → Integração com API externa
├── controller/     → Endpoints REST
├── dto/            → Objetos de transferência de dados
├── exception/      → Exceções customizadas e handler global
├── model/          → Entidade JPA
├── repository/     → Acesso ao banco de dados
├── service/        → Regras de negócio
└── AppConfig.java  → Configuração de beans
```

## 🔗 Endpoints

### Cadastrar veículo
```
POST /api/veiculos
```
**Body:**
```json
{
  "proprietario": "José da Silva",
  "cpf": "203.397.390-53",
  "placa": "ABC1234"
}
```
**Resposta (201 Created):**
```json
{
  "id": 1,
  "proprietario": "José da Silva",
  "cpf": "203.397.390-53",
  "placa": "ABC1234",
  "marca": "Chevrolet",
  "modelo": "Suburban 1500",
  "chassi": "5TDBKRFH2FS979708",
  "licenciado": true
}
```

### Listar todos os veículos
```
GET /api/veiculos
```

### Buscar veículo por ID
```
GET /api/veiculos/{id}
```

## ⚠️ Respostas de erro

Todos os erros retornam um JSON padronizado:

```json
{
  "status": 409,
  "mensagem": "CPF já cadastrado"
}
```

| Status | Situação |
|--------|----------|
| 400 | Dados de entrada inválidos |
| 404 | Veículo não encontrado |
| 409 | CPF ou Placa já cadastrados |
| 422 | Falha na validação dos campos |
| 502 | Erro ao consultar API externa |

## ✅ Validações

- `proprietario` — obrigatório
- `cpf` — obrigatório e validado no formato brasileiro
- `placa` — obrigatório, aceita formato antigo (`ABC1234`) e Mercosul (`ABC1D23`)
- CPF e Placa são únicos no sistema

## ▶️ Como executar

**Pré-requisitos:** Java 21+ e Maven instalados.

```bash
# Clone o repositório
git clone https://github.com/Lucass-marques/desafio-backend-java-jr.git

# Entre na pasta
cd desafio-backend-java-jr

# Execute
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

O console do H2 pode ser acessado em `http://localhost:8080/h2-console` com a URL `jdbc:h2:mem:autoscoredb`.
