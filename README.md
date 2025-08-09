# API Commerce Simples

Este projeto é parte do Desafio Final do Bootcamp **Arquiteto de Software** e consiste no desenvolvimento de uma API REST utilizando arquitetura MVC para gerenciar dados de Clientes, Produtos e pedidos simulando um sistema de uma empresa de vendas online.

## 🔧 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven

## 📐 Arquitetura do Projeto

O projeto segue o padrão **MVC** (Model-View-Controller), com separação clara das responsabilidades:

```
src/main/java/com/xpeducacao/desafio_final_app/
├── controller # Camada de controle (exposição de endpoints REST)
├── dto # Objetos de transferência de dados entre camadas
├── model # Entidades JPA do domínio (Cliente, Produto, Pedido, PedidoItem)
├── repository # Interfaces de persistência com Spring Data JPA
├── service # Camada de serviço com regras de negócio
└── DesafioFinalAppApplication # Classe principal que inicia a aplicação
```

## 🧩 Funcionalidades da API

A API disponibiliza os seguintes endpoints para a entidade `Cliente`:

| Método HTTP | Endpoint                 | Descrição                           |
|-------------|--------------------------|-------------------------------------|
| `GET`       | `/clientes`              | Lista todos os clientes             |
| `GET`       | `/clientes/{id}`         | Busca cliente por ID                |
| `GET`       | `/clientes/buscar`       | Busca clientes pelo nome            |
| `GET`       | `/clientes/contar`       | Conta total de clientes             |
| `POST`      | `/clientes`              | Cria                                |
| `PUT`       | `/clientes`              | Atualiza                            |
| `DELETE`    | `/clientes/{id}`         | Remove cliente por ID               |

A API disponibiliza os seguintes endpoints para a entidade `Produto`:

| Método HTTP | Endpoint                 | Descrição                           |
|-------------|--------------------------|-------------------------------------|
| `GET`       | `/produtos`              | Lista todos os produtos             |
| `GET`       | `/produtos/{id}`         | Busca produto por ID                |
| `GET`       | `/produtos/buscar`       | Busca produtos pelo nome            |
| `GET`       | `/produtos/contar`       | Conta total de produtos             |
| `POST`      | `/produtos`              | Cria                                |
| `PUT`       | `/produtos`              | Atualiza                            |
| `DELETE`    | `/produtos/{id}`         | Remove produto por ID               |

A API disponibiliza os seguintes endpoints para a entidade `Pedido`:

| Método HTTP | Endpoint                     | Descrição                                 |
|-------------|------------------------------|-------------------------------------------|
| `GET`       | `/pedidos`                   | Lista todos os pedidos                    |
| `GET`       | `/pedidos/{id}`              | Busca pedido por ID                       |
| `GET`       | `/pedidos/buscar`            | Lista pedidos pelo nome de um cliente     |
| `GET`       | `/pedidos/contar`            | Conta total de pedidos                    |
| `POST`      | `/pedidos`                   | Cria                                      |
| `PUT`       | `/pedidos`                   | Atualiza                                  |
| `DELETE`    | `/pedidos/{id}`              | Remove pedido por ID                      |

## 📂 Documentação da Arquitetura
Toda a documentação referente à arquitetura do projeto está localizada na pasta docs/architecture/, que contém:

Diagramas C4/ – Diagramas no modelo C4, representando os diferentes níveis de visão do sistema (Contexto, Contêiner, Componentes e, se necessário, Código).

Diagramas de Classes/ – Diagramas UML de classes, descrevendo as estruturas e relações entre as entidades do sistema.
