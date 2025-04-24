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

# 🚀 Integração com HubSpot

Este guia mostra como configurar seu aplicativo no HubSpot e integrá-lo com seu projeto Spring Boot para autenticação e recebimento de webhooks.

---

## 🧾 Pré-requisitos

- Conta no [HubSpot](https://developers.hubspot.com/)
- Aplicação Spring Boot rodando localmente (ex: `http://localhost:8080`)
- [Ngrok](https://ngrok.com/) instalado e configurado

---

## 🔧 Passo a passo

1. **Crie uma conta no HubSpot**  
   Se ainda não tiver, acesse [HubSpot Developer](https://developers.hubspot.com/) e crie uma conta.

2. **Acesse o painel de desenvolvedor**  
   Vá até a seção **"Aplicativos"** e clique em **"Criar aplicativo"** (ou selecione um existente).

3. **Configurar autenticação OAuth**
    - Vá para a aba **"Autenticação"**
    - Copie o **Client ID** e o **Client Secret**
    - Adicione essas credenciais no seu projeto (em variáveis de ambiente ou no arquivo `application.properties`):

      ```properties
      hubspot.client-id=SEU_CLIENT_ID
      hubspot.client-secret=SEU_CLIENT_SECRET
      ```

4. **Adicionar URL de redirecionamento**
    - Na aba **"URLs de redirecionamento"**, adicione:
      ```
      http://localhost:8080/api/v1/oauth/callback
      ```

5. **Selecionar escopos (Scopes)**
    - `oauth`
    - `crm.objects.contacts.write`
    - `crm.objects.contacts.read`

6. **Salvar alterações**

7. **Configurar Webhook**
    - Vá até a aba **"Webhooks"**
    - Adicione a URL pública gerada pelo ngrok com o endpoint que você criará na aplicação:
      ```
      https://exemplo.ngrok-free.app/webhook
      ```

8. **Criar assinatura de eventos**
    - Clique em **"Criar assinatura"**
    - Tipo de objeto: **Contato**
    - Evento monitorado: **Criado**

9. **Salvar e testar**  
   Agora sua integração está pronta! Crie um contato no HubSpot e verifique se o evento chega corretamente no seu endpoint `/webhook`.

---

