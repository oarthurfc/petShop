# Projeto PetShopDTI

Este projeto é uma aplicação full-stack que permite aos usuários encontrarem o melhor pet shop para serviços de banho e tosa com base na data selecionada e no número de pets pequenos e grandes. O backend é construído com Java e Spring Boot, enquanto o frontend é desenvolvido usando React e TypeScript com Vite como ferramenta de build.

## Índice

- [Funcionalidades](#funcionalidades)
- [Requisitos](#requisitos)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Primeiros Passos](#primeiros-passos)
  - [Configuração do Backend](#configuração-do-backend)
  - [Configuração do Frontend](#configuração-do-frontend)
- [Endpoint da API](#endpoint-da-api)
- [Premissas Assumidas](#premissas-assumidas)
- [Decisões de Projeto](#decisões-de-projeto)

## Funcionalidades

- **Backend**:
  - API RESTful com endpoints para encontrar o melhor pet shop com base nos critérios fornecidos.
  - Integração com um banco de dados MySQL para armazenar dados dos pet shops.
  - Configuração de CORS para permitir requisições do frontend.

- **Frontend**:
  - Interface amigável para inserir dados e exibir resultados.
  - Seletor de data para escolher a data.
  - Campos de formulário para o número de pets pequenos e grandes.
  - Exibe o nome do melhor pet shop e o custo total.
 
## Requisitos

Certifique-se de ter as seguintes dependências instaladas no seu ambiente:

- Node.js (versão 16 ou superior)
- npm (versão 7 ou superior)
- Java **(versão 21)**
- MySQL

## Tecnologias Utilizadas

- **Backend**:
  - Java **21**
  - Spring Boot
  - MySQL
  - Maven

- **Frontend**:
  - React
  - TypeScript
  - Vite
  - CSS

## Primeiros Passos

### Configuração do Backend

1. **Clone o repositório**:
    ```sh
    git clone https://github.com/oarthurfc/testeDTI
    cd PetShopDTI
    ```

2. **Configure o banco de dados MySQL**:
    - No arquivo application.properties localizado em src/main/resources, ajuste as credenciais do banco de dados conforme instalado na sua máquina previamente.

3. **Construa e execute o backend**:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

4. **Configuração de CORS**:
    - Certifique-se de que a classe `CorsConfig` esteja configurada corretamente no pacote `config` para permitir requisições do frontend.

### Configuração do Frontend

1. **Navegue até o diretório do frontend**:
    ```sh
    cd ../petshop-frontend
    ```

2. **Instale as dependências**:
    ```sh
    npm install
    ```

3. **Execute o frontend**:
    ```sh
    npm run dev
    ```

4. **Acesse a aplicação**:
    - Abra seu navegador e navegue para `http://localhost:5173`.

## Endpoint da API

### `GET /api/petshops/melhor-petshop`

Encontra o melhor pet shop com base na data selecionada e no número de pets.

**Parâmetros de Query**:
- `data` (string): Data no formato `dd/MM/yyyy`.
- `qtdPequenos` (int): Número de pets pequenos.
- `qtdGrandes` (int): Número de pets grandes.


**Resposta**:
```json
{
    "nome": "Nome do PetShop",
    "precoTotal": 123.45
}
```

## Premissas assumidas
- O sistema assume que todos os dados de pet shops estão previamente cadastrados no banco de dados.
- A aplicação considera que os preços dos serviços de banho e tosa não mudam frequentemente.


## Decisões de Projeto

- **Arquitetura em camads:** O projeto utiliza o padrão de arquitetura em camadas para separar as responsabilidades e facilitar a manutenção e evolução do sistema.
- **Princípios SOLID:** A aplicação foi desenvolvida seguindo os princípios SOLID para garantir um código mais modular, reutilizável e fácil de entender.
- **Padrão Strategy:** Utilizado para calcular o preço final dos serviços, permitindo uma fácil extensão para diferentes tipos de cálculos de adicionais (fixo ou percentual).
- **DTOs (Data Transfer Objects):** Utilizados para transferir dados entre o front-end e o back-end, garantindo que apenas as informações necessárias sejam enviadas.
- **Configuração de CORS:** Foi configurado CORS no back-end para permitir que o front-end em React se comunique com o servidor Spring Boot.
