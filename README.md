# 💰 Budge

API REST para gerenciamento de transações financeiras, desenvolvida
como projeto prático para estudo de Java, Spring Boot, arquitetura de
software, persistência de dados e integração com Inteligência Artificial.

---

## 📌 Sobre o projeto

O **Budge** é uma aplicação backend criada para permitir o registro e
a consulta de transações financeiras.

A aplicação trabalha com informações como:

- Descrição da transação;
- Categoria;
- Valor;
- Identificador da transação.

Além do gerenciamento tradicional das transações, o projeto possui uma
integração com **Spring AI**, permitindo utilizar Inteligência Artificial
para auxiliar no processamento das informações financeiras.

O projeto foi desenvolvido com foco em aprender na prática conceitos
utilizados no desenvolvimento backend com Java e Spring.

---

## 🎯 Objetivos

O principal objetivo do projeto é colocar em prática conceitos de:

- Programação Orientada a Objetos;
- Arquitetura de aplicações;
- APIs REST;
- Injeção de dependências;
- Persistência de dados;
- Banco de dados relacional;
- Docker;
- Testes;
- Integração com APIs de Inteligência Artificial.

---

## 🏗️ Arquitetura

O projeto utiliza uma separação entre domínio, aplicação e infraestrutura.

```text
src/main/java/org/example/budge

├── application
│   ├── input
│   └── output
│
├── domain
│   ├── Category
│   ├── Transaction
│   └── TransactionRepository
│
└── infrastructure
    ├── persistence
    │   ├── entity
    │   ├── repository
    │   └── http
    │
    └── ...