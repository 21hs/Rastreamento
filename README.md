# Rastreador de Encomendas

Este é um projeto simples de rastreamento de encomendas implementado em Java. O projeto utiliza MySQL como banco de dados para armazenar informações sobre usuários, encomendas e status.


## Pré-requisitos

- Java 8 ou superior
- Maven 3.x
- MySQL Server

## Configuração

### 1. Clone o Repositório ou Baixe os Arquivos

Clone ou baixe os arquivos do projeto para sua máquina local.

### 2. Configuração do Banco de Dados

- Crie um banco de dados no MySQL chamado `nome_do_banco` (substitua pelo nome desejado).
- Execute os seguintes comandos SQL para criar as tabelas necessárias:

```sql
CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    telefone VARCHAR(15)
);

CREATE TABLE Encomenda (
    id_encomenda INT AUTO_INCREMENT PRIMARY KEY,
    codigo_rastreio VARCHAR(50),
    data_envio DATE,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Status (
    id_status INT AUTO_INCREMENT PRIMARY KEY,
    id_encomenda INT,
    status VARCHAR(100),
    data_status DATE,
    FOREIGN KEY (id_encomenda) REFERENCES Encomenda(id_encomenda)
);
```
Configuração das Credenciais do Banco de Dados
Abra o arquivo DatabaseConnection.java e altere as variáveis URL, USER e PASSWORD com as informações de acesso ao seu banco de dados MySQL.

```
private static final String URL = "jdbc:mysql://localhost:3306/nome_do_banco"; // Substitua com seu nome de banco
private static final String USER = "usuario"; // Substitua com seu nome de usuário
private static final String PASSWORD = "senha"; // Substitua com sua senha
```
Executando o Projeto
1. Compilação e Instalação
Navegue até o diretório do projeto e execute o comando abaixo para compilar e instalar o projeto:
```
mvn clean install
```
 Execução do Projeto
Após a instalação, execute o projeto com o comando abaixo:
```
mvn exec:java -Dexec.mainClass="main.Main"
```
Certifique-se de substituir "main.Main" pela classe principal do seu projeto, caso necessário.

Testes
Para executar testes automatizados, utilize o Maven com o seguinte comando:
```
mvn test
```

## Contribuições
Sinta-se à vontade para enviar pull requests ou relatar problemas. Para contribuir, siga estas etapas:

Faça um fork deste repositório.
Crie uma nova branch para suas alterações.
Envie um pull request com uma descrição detalhada das mudanças realizadas.


### Como Usar o Documento

1. **Clone ou baixe o projeto**: Copie e cole as instruções do código SQL e configurações do banco de dados diretamente na sua ferramenta de gerenciamento de banco de dados.
2. **Compilação e execução**: Siga as instruções de compilação com o Maven para instalar e rodar o projeto.
3. **Contribuição**: O projeto está aberto para contribuições. Você pode fazer um fork, realizar alterações e submeter um pull request.

Esse arquivo `README.md` serve como guia completo para configuração, execução e contribuição ao projeto.



