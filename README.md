[README.md](https://github.com/user-attachments/files/25433734/README.md)
# LiterAlura - Catálogo de Livros

Sistema de catálogo de livros que consome a API Gutendex para buscar informações sobre livros em domínio público e armazená-los em um banco de dados PostgreSQL.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- PostgreSQL
- Jackson (para conversão JSON)
- Maven

## 📋 Pré-requisitos

- JDK 17 ou superior
- Maven 3.6+
- PostgreSQL instalado e rodando
- IntelliJ IDEA (recomendado)

## 🔧 Configuração do Banco de Dados

### 1. Criar o banco de dados no PostgreSQL

Abra o terminal do PostgreSQL (psql) ou use o pgAdmin e execute:

```sql
CREATE DATABASE literalura_db;
```

### 2. Configurar as credenciais

Edite o arquivo `src/main/resources/application.properties` e ajuste as seguintes linhas:

```properties
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_AQUI
```

Substitua `SUA_SENHA_AQUI` pela senha do seu usuário PostgreSQL.

Se necessário, ajuste também a porta (padrão: 5432):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura_db
```

## 📦 Instalação e Execução

### ⚡ Setup Automático (Recomendado)

**Windows:**
```cmd
setup.bat
```

**Linux/Mac:**
```bash
chmod +x setup.sh
./setup.sh
```

### 🔧 Setup Manual:

### Usando IntelliJ IDEA:

1. **Abrir o projeto:**
   - File → Open → Selecione a pasta `literalura`

2. **Aguardar o download das dependências:**
   - O IntelliJ irá automaticamente baixar as dependências do Maven

3. **Executar a aplicação:**
   - Localize a classe `LiteraluraApplication.java`
   - Clique com botão direito → Run 'LiteraluraApplication'
   - Ou use o atalho: Shift + F10

### Usando linha de comando:

```bash
# Navegue até a pasta do projeto
cd literalura

# Compile o projeto
mvn clean install

# Execute a aplicação
mvn spring-boot:run
```

## 💻 Funcionalidades

O sistema apresenta um menu interativo com as seguintes opções:

### 1. Buscar livro pelo título
- Busca livros na API Gutendex
- Salva automaticamente no banco de dados
- Evita duplicação de livros e autores

### 2. Listar livros registrados
- Exibe todos os livros salvos no banco de dados
- Ordenados alfabeticamente por título

### 3. Listar autores registrados
- Exibe todos os autores salvos
- Mostra os livros de cada autor

### 4. Listar autores vivos em determinado ano
- Filtra autores que estavam vivos em um ano específico
- Considera anos de nascimento e falecimento

### 5. Listar livros por idioma
- Exibe quantidade de livros por idioma
- Mostra lista dos livros encontrados
- Códigos suportados: en, pt, es, fr, de, it

## 📊 Estrutura do Projeto

```
literalura/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/literalura/
│   │   │       ├── model/           # Entidades JPA
│   │   │       │   ├── Autor.java
│   │   │       │   └── Livro.java
│   │   │       ├── dto/             # Data Transfer Objects
│   │   │       │   ├── AutorDTO.java
│   │   │       │   ├── LivroDTO.java
│   │   │       │   └── GutendexResponseDTO.java
│   │   │       ├── repository/      # Repositórios JPA
│   │   │       │   ├── AutorRepository.java
│   │   │       │   └── LivroRepository.java
│   │   │       ├── service/         # Lógica de negócio
│   │   │       │   ├── ConsumoAPI.java
│   │   │       │   └── LiteraluraService.java
│   │   │       └── LiteraluraApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## 🔍 Exemplos de Uso

### Buscar um livro:
```
Escolha uma opção: 1
Digite o título do livro: pride and prejudice
```

### Listar autores vivos em um ano:
```
Escolha uma opção: 4
Digite o ano: 1890
```

### Listar livros por idioma:
```
Escolha uma opção: 5
Digite o código do idioma: en
```

## 🐛 Solução de Problemas

## 🐛 Solução de Problemas

### Erro: java.lang.ExceptionInInitializerError - TypeTag UNKNOWN

Este erro ocorre por incompatibilidade de versões. Soluções:

1. **Verificar Java 17:**
   ```bash
   java -version
   ```

2. **No IntelliJ IDEA:**
   - `Ctrl+Alt+Shift+S` → Project → SDK: Java 17
   - `File → Settings → Build Tools → Maven → Runner → JRE: Java 17`

3. **Invalidar Cache:**
   - `File → Invalidate Caches... → Invalidate and Restart`

4. **Recarregar Maven:**
   - Aba Maven (lateral direita) → Clique no ícone 🔄 Reload

5. **Limpar e recompilar:**
   ```bash
   mvn clean install -DskipTests
   ```

**Consulte o arquivo `TROUBLESHOOTING.md` para mais detalhes!**

### Erro de conexão com o banco de dados:
- Verifique se o PostgreSQL está rodando
- Confirme usuário e senha no `application.properties`
- Certifique-se de que o banco `literalura_db` foi criado

### Livro já registrado:
- O sistema não permite duplicatas
- Uma mensagem será exibida informando que o livro já existe

### Nenhum resultado encontrado:
- Verifique a ortografia do título
- Tente buscar com palavras-chave ou título parcial

## 📝 Observações

- A API Gutendex é gratuita e não requer autenticação
- Os livros são em domínio público
- O sistema salva automaticamente o primeiro resultado da busca
- Autores são reutilizados se já existirem no banco

## 📄 Licença

Projeto educacional desenvolvido como desafio de programação.

## 👨‍💻 Autor

Ronaldo Vilela\
Desenvolvedor Backend em evolução 🚀

GitHub: https://github.com/Ronaldo-Vilela
