# LiterAlura 📚

Uma aplicação de linha de comando desenvolvida em Java com Spring Boot para catalogar livros e autores, consumindo dados da API Gutendex.

## 📋 Descrição do Projeto

**LiterAlura** é uma aplicação de linha de comando que permite criar um catálogo completo de livros e autores. A aplicação se conecta à API Gutendex para buscar informações sobre livros, armazena os dados em um banco PostgreSQL e oferece diversas funcionalidades de consulta.

Este projeto foi desenvolvido como parte de um desafio de programação para consolidar conhecimentos em:

- Configuração de ambiente Java e Spring Boot
- Comunicação com APIs REST externas
- Mapeamento de dados JSON para objetos Java (Jackson)
- Persistência de dados com Spring Data JPA e PostgreSQL
- Criação de consultas personalizadas com JPQL
- Desenvolvimento de interface de linha de comando interativa

## 🛠 Tecnologias Utilizadas

- **Linguagem**: Java 17+
- **Framework**: Spring Boot 3.2.3
- **Gerenciador de Dependências**: Maven
- **Banco de Dados**: PostgreSQL
- **Persistência**: Spring Data JPA
- **Processamento JSON**: Jackson

## ⚡ Funcionalidades

A aplicação oferece um menu interativo com as seguintes opções:

1. **Buscar livro por título** - Pesquisa na API Gutendex e salva o resultado no banco
2. **Listar todos os livros** - Exibe todos os livros registrados no catálogo
3. **Listar autores** - Mostra todos os autores cadastrados
4. **Listar autores vivos por ano** - Filtra autores que estavam vivos em um ano específico
5. **Listar livros por idioma** - Organiza livros por idioma de publicação
6. **Sair** - Encerra a aplicação

## 🚀 Como Executar

### Pré-requisitos

- Java JDK 17+
- Apache Maven
- PostgreSQL
- IDE de sua preferência

### Configuração do Banco

1. Inicie o servidor PostgreSQL
2. Crie o banco de dados:
   ```sql
   CREATE DATABASE literalura;
   ```

3. Configure o arquivo `application.properties` em `src/main/resources/`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_AQUI
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

⚠️ **Importante**: O arquivo `application.properties` não está incluído no repositório por questões de segurança.

### Executando

1. Clone o repositório
2. Configure o `application.properties` com suas credenciais
3. Execute a classe `LiteraluraApplication`
4. Interaja com o menu no console

## 💡 Exemplo de Uso

```
-------------------------------------------------
Escolha uma opção:
1 - Buscar livro por título
2 - Listar todos os livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um determinado ano
5 - Listar livros por idioma
0 - Sair
-------------------------------------------------

1
Digite o título do livro que você quer buscar:
Frankenstein
Livro encontrado e salvo no banco de dados:
Título: Frankenstein; Or, The Modern Prometheus | Autor: Shelley, Mary Wollstonecraft | Idioma: en
```

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para:

- Reportar bugs
- Sugerir novas funcionalidades
- Enviar pull requests

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!
