# API RESTful - Spring Boot

Este projeto é parte do Desafio Final do Bootcamp **Arquiteto de Software** e consiste no desenvolvimento de uma API RESTful utilizando arquitetura MVC para gerenciar dados de Clientes, Produtos e pedidos simulando um sistema de uma empresa de vendas online.

## 🔧 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven

## 📐 Arquitetura do Projeto

O projeto segue o padrão **MVC** (Model-View-Controller), com separação clara das responsabilidades:

src/main/java/com/xpeducacao/desafio_final_app/
├── controller # Camada de controle (exposição de endpoints REST)
├── model # Entidades JPA do domínio (Cliente, Produto, Pedido, PedidoItem)
├── repository # Interfaces de persistência com Spring Data JPA
├── service # Camada de serviço com regras de negócio
└── DesafioFinalAppApplication # Classe principal que inicia a aplicação
