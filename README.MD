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

### 👥 Lista de contatos

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


### 👤 Cria um novo contato:
````
    POST /api/v1/contacts
    Cria um novo contato na conta HubSpot vinculada.
    {
        "firstName": "João",
        "lastName": "Silva",
        "email": "joao.silva@example.com"
    }
````

### ❌ Deleta um Contato:
````
    DELETE /api/v1/contacts/{id}
    Deleta um contato a partir do ID
``````



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

## 🔧 Configuração no HubSpot

1. **Crie uma conta no HubSpot**  
   Acesse [HubSpot Developer](https://developers.hubspot.com/) e crie sua conta.

2. **Crie um aplicativo no HubSpot**  
   Vá até **"Aplicativos"** no painel de desenvolvedor e clique em **"Criar aplicativo"** (ou edite um existente).

3. **Configurar autenticação OAuth**
    - Vá até a aba **"Autenticação"**
    - Copie o **Client ID** e o **Client Secret**
    - No seu projeto, adicione essas credenciais no `application.properties` ou no `.env`:
      ```properties
      hubspot.client-id=SEU_CLIENT_ID
      hubspot.client-secret=SEU_CLIENT_SECRET
      ```

4. **Adicionar URL de redirecionamento**
    - Em **"URLs de redirecionamento"**, adicione:
      ```
      http://localhost:8080/api/v1/oauth/callback
      ```

5. **Selecionar escopos (Scopes)**
    - `oauth`
    - `crm.objects.contacts.write`
    - `crm.objects.contacts.read`

6. **Configurar Webhook**
    - Na aba **"Webhooks"**, adicione o endpoint gerado pelo Ngrok:
      ```
      https://seu-endereco.ngrok-free.app/webhook
      ```

7. **Criar assinatura de eventos**
    - Objeto: **Contato**
    - Evento: **Criado**

8. **Teste**
    - Crie um novo contato no HubSpot e verifique se o evento chega ao endpoint `/webhook`.

---

# 🧪 Executando o Projeto

Você pode rodar este projeto de duas formas: usando **Docker** ou diretamente na **IDE**.

---

## 🧾 Pré-requisitos

- Java 17 ou superior
- Docker e Docker Compose instalados (se for usar Docker)
- IDE com suporte a Spring Boot (como IntelliJ ou Eclipse)

---

## 🐳 Rodando com Docker

1. **Clonar o repositório**
    ```bash
    git clone https://github.com/rafaelsantos01/meetime.git
    cd meetime
    ```

2. **Configurar variáveis de ambiente**  
   Edite o arquivo `docker-compose.yml` e adicione as variáveis necessárias.


3. **Build do projeto**
    ```bash
    ./mvnw clean package
    ```

4. **Subir os containers**
    ```bash
    docker-compose up
    ```

---

## 💻 Rodando na IDE

1. **Clonar o repositório**
    ```bash
    git clone https://github.com/rafaelsantos01/meetime.git
    cd meetime
    ```

2. **Configurar variáveis de ambiente**  
   Você pode usar um dos métodos abaixo:
    - Editar o arquivo `src/main/resources/application.properties`
    - Criar um arquivo `.env` com as variáveis necessárias

3. **Executar a aplicação**  
   Na sua IDE, execute a classe principal do projeto.


# 🛜 Usando o Ngrok

## 🛠️ Instalação do Ngrok
1. **Baixar o Ngrok**  
   Acesse [ngrok.com](https://ngrok.com/download) e siga as instruções de instalação para o seu sistema operacional.
2. **Autenticar o Ngrok**  
   Após a instalação, você precisará autenticar o Ngrok com sua conta. Execute o seguinte comando no terminal:
   ```bash
   ngrok authtoken SEU_TOKEN
   ```
   Substitua `SEU_TOKEN` pelo token que você encontra na sua conta do Ngrok.
3. **Iniciar o Ngrok**  
   Para expor sua aplicação Spring Boot, execute o seguinte comando:
   ```bash
   ngrok http 8080
   ```

