# 🔗 HubSpot Integration - Spring Boot

Este projeto tem como objetivo integrar uma aplicação Spring Boot com a API do **HubSpot**, utilizando **OAuth 2.0** para autenticação e autorização, e permitindo operações de leitura e escrita de contatos.

---

## 📁 Estrutura do Projeto

```bash

com.projeto.hubspot_integration
├── client        # Interfaces Feign para comunicação com APIs externas
├── controller    # Endpoints REST da aplicação
├── dto           # Objetos de transferência de dados
├── exceptions    # Manipulação de erros e exceções customizadas
├── service       # Camada de lógica de negócio
├── token         # Lógica de obtenção e gerenciamento de tokens OAuth
└── webhook       # Lógica de recepção de webhooks HubSpot (se aplicável)
```
## 📁 Variáveis de Ambiente

```bash

CLIENT_ID_HUBSPOT=
CLIENT_SECRET_HUBSPOT=
REDIRECT_URI_HUBSPOT=http://localhost:8080/api/v1/oauth/callback
SCOPE_HUBSPOT=oauth,crm.objects.contacts.write,crm.objects.contacts.read
BASE_URL_API=https://api.hubapi.com
BASE_URL=https://app.hubspot.com
```

## 📡 Endpoints
````
🔐 Autenticação
GET /api/v1/oauth/authorization
Inicia o processo de login com o HubSpot.

GET /api/v1/oauth/callback
Recebe o callback do HubSpot e gera o token.
````

## 👥 Contatos

````
GET /api/v1/contacts
Lista todos os contatos da conta HubSpot vinculada.

 [
    {
        "id": "116494280747",
        "email": "bh@hubspot.com",
        "firstName": "Brian",
        "lastName": "Halligan (Sample Contact)",
        "createdAt": "2025-04-23T23:33:54.255Z"
    }
 ]
````


## Corpo da requisição:
````
    POST /api/v1/contacts
    Cria um novo contato
    {
        "firstName": "João",
        "lastName": "Silva",
        "email": "joao.silva@example.com"
    }
````



## 🛠️ Tecnologias Utilizadas
````
Java 17

Spring Boot 3.4.4

Spring Web

Spring Validation

Spring Cloud OpenFeign

Lombok
````



