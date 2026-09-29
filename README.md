# 🛒 E-commerce - Loja do Mestre André

Projeto de e-commerce desenvolvido com o objetivo de praticar e demonstrar conhecimentos em **Java, Spring Boot, APIs REST, JPA, MySQL, DTOs, validações e organização de projetos em camadas**.

O projeto está sendo desenvolvido como parte do meu portfólio para aplicar na prática os conhecimentos adquiridos durante meus estudos de desenvolvimento Back-end com Java e Spring Boot.

---

## 🚀 Tecnologias utilizadas

### Back-end

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Security Crypto
- Lombok
- MySQL
- Maven

### Front-end

- HTML5
- CSS3
- JavaScript
- Fetch API
- LocalStorage

### Ferramentas

- IntelliJ IDEA
- MySQL Workbench
- Postman
- Git
- GitHub

---

## 🏗️ Arquitetura

O projeto utiliza uma **Arquitetura em Camadas (Layered Architecture)**, separando as responsabilidades da aplicação.

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

A estrutura principal do projeto está organizada da seguinte forma:

```text
src
└── main
    └── java
        └── E_commerce.e_commerce
            │
            ├── config
            │   └── SecurityConfig
            │
            └── entitys
                │
                ├── category
                │   ├── controller
                │   ├── repository
                │   ├── service
                │   └── Category
                │
                ├── products
                │   ├── controller
                │   ├── productsDTO
                │   ├── repository
                │   ├── service
                │   └── Product
                │
                └── user
                    ├── Controller
                    ├── Repository
                    ├── Service
                    ├── userDTO
                    ├── User
                    └── UserRole
```

---

## 👤 Usuários

O sistema possui cadastro, login e gerenciamento de usuários.

Cada usuário possui:

- Nome
- Nome de usuário
- E-mail
- Telefone
- Senha
- Perfil de acesso

O sistema possui dois níveis de usuário:

```text
USER
ADMIN
```

Novos usuários são registrados como `USER` por padrão.

Administradores possuem acesso às funcionalidades administrativas da aplicação.

---

## 🔐 Autenticação

O sistema utiliza **BCrypt** para armazenamento seguro das senhas.

As senhas não são armazenadas diretamente no banco de dados. Antes de serem salvas, elas passam pelo processo de hash utilizando `BCryptPasswordEncoder`.

Durante o login, a senha informada pelo usuário é comparada com o hash armazenado no banco de dados.

### Fluxo de login

```text
Usuário
   ↓
Frontend
   ↓
POST /users/login
   ↓
UserController
   ↓
UserService
   ↓
BCrypt
   ↓
Banco de dados
```

Após o login, as informações do usuário são armazenadas no `LocalStorage` do navegador para manter o estado do usuário no front-end.

---

## 👑 Controle de administrador

O projeto possui um sistema de roles:

```text
USER
ADMIN
```

O cadastro normal cria automaticamente usuários com:

```text
role = USER
```

Usuários com `ADMIN` possuem acesso ao painel administrativo através do menu da aplicação.


---

# 📦 Produtos

O sistema possui uma estrutura de CRUD para produtos.

Cada produto possui:

- ID
- Nome
- Descrição
- Preço
- Quantidade em estoque
- URL da imagem
- Categoria

Os produtos possuem relacionamento com categorias utilizando JPA.

```java
@ManyToOne
```

---

## 🏷️ Categorias

Os produtos são relacionados a categorias.

Exemplo:

```text
Categoria
    ↓
Jogos

Produto
    ↓
God of War Ragnarok
```

Uma categoria pode possuir vários produtos, enquanto cada produto pertence a uma categoria.

---

## Categorias

A aplicação possui uma estrutura própria para gerenciamento de categorias, utilizando:

```text
Controller
Service
Repository
Entity
```

---

# 📝 DTOs

O projeto utiliza **DTOs (Data Transfer Objects)** para controlar os dados enviados e retornados pela API.

Exemplo de cadastro de produto:

```json
{
    "name": "God of War Ragnarok",
    "description": "Jogo de ação e aventura",
    "price": 199.90,
    "quantityStock": 10,
    "imageUrl": "https://exemplo.com/imagem.jpg",
    "categoryId": 1
}
```

O uso de DTOs permite separar os dados utilizados pela API das entidades diretamente relacionadas ao banco de dados.

---

# ✅ Validações

O projeto utiliza Bean Validation para validar os dados recebidos pela API.

Algumas das validações utilizadas:

```java
@NotBlank
@NotNull
@Email
@Size
@Positive
@PositiveOrZero
```

Essas validações são utilizadas nos DTOs para evitar o recebimento de dados inválidos.

---

# 🖥️ Front-end

O front-end está sendo desenvolvido utilizando HTML, CSS e JavaScript puro.

A comunicação com o back-end é realizada através da API REST utilizando `fetch()`.

Exemplo:

```javascript
fetch("http://localhost:8080/products")
```

O sistema também utiliza `LocalStorage` para armazenar temporariamente as informações do usuário no navegador.

O menu da aplicação também é atualizado de acordo com o usuário logado.

---

# 🛠️ Funcionalidades implementadas

- [x] Cadastro de usuários
- [x] Login de usuários
- [x] Criptografia de senhas com BCrypt
- [x] Atualização de perfil
- [x] Exclusão de usuário
- [x] Diferenciação entre usuário e administrador
- [x] CRUD de categorias
- [x] CRUD de produtos
- [x] Relacionamento entre produtos e categorias
- [x] Validação dos dados recebidos pela API
- [x] Comunicação entre front-end e back-end
- [x] Menu dinâmico de acordo com o usuário logado
- [x] Estrutura inicial do painel administrativo

---

# 🎯 Objetivo do projeto

Este projeto foi desenvolvido com foco em **aprendizado e construção de portfólio**, buscando colocar em prática conceitos importantes do desenvolvimento Back-end com Java e Spring Boot.

Entre os principais conceitos praticados estão:

- Programação Orientada a Objetos
- Arquitetura em camadas
- APIs REST
- Spring Boot
- Spring Data JPA
- Relacionamentos entre entidades
- DTOs
- Validação de dados
- Criptografia de senhas
- MySQL
- Git e GitHub
- Integração entre Front-end e Back-end

---

# 👨‍💻 Autor

**André Rodrigues Miranda Junior**

Desenvolvedor Java Back-end em formação, com foco em:

- Java
- Spring Boot
- APIs REST
- Banco de dados
- MySQL
- JPA/Hibernate
- Git/GitHub

Buscando uma oportunidade como **Desenvolvedor Java Back-end Júnior / Trainee**.

---

## 📌 Status

🚧 **Em desenvolvimento**

O projeto está sendo desenvolvido continuamente, com novas funcionalidades sendo adicionadas conforme avanço nos estudos de Java e Spring Boot.
