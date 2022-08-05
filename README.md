# Teste Prático React

## Cotefácil
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/bob-okamura/test1/blob/main/LICENCE)

# Questão 1

### Desenvolva uma aplicação Java com JSF + richfaces (ou primefaces). Esta aplicação listará os planetas encontrados em Star Wars.

## Funcionalidades desejadas:

-  Adicionar um planeta (com nome, clima e terreno)
-  Listar planetas
-  Buscar por nome
-  Buscar por ID
-  Remover planeta

## Requisitos:

- Para cada planeta, os seguintes dados devem ser obtidos do banco de dados da aplicação, sendo inserido manualmente: Nome, Clima e Terreno
-  Para cada planeta também devemos ter a quantidade de aparições em filmes, que podem ser obtidas pela API pública do Star Wars: https://swapi.dev/about, buscando pelo nome do planeta

## Linguagens: Java

## Bancos: SQL em memória

***Um bom software é um software bem testado.***

## Padrão
![Web 1](https://user-images.githubusercontent.com/78389467/183221293-2a133abe-ece9-436d-8bcc-3b524ac7d1b8.png)

## Como testar a funcionalidade do projeto:

## 1) Importação do projeto para teste
Fazendo o clone do projeto: 
- Copie o endereço ```git@github.com:bob-okamura/test1.git```, selecione uma pasta de sua preferência, abra o Git Bash Here.
  - Execute os seguintes comandos:
  ```
  git clone git@github.com:bob-okamura/test1.git
  ```
  - Abra sua IDE e ```File```->```import```->```Existing Maven Projects```->abra a pasta do diretório escolhido.
    
## 2) Testando
- Aguarde a IDE fazer os imports e para testar, suba o servidor.

  - Para o banco H2: ```http://localhost:8080/h2-console```
  - Para a abrir a página: ```http://localhost:8080/planetas.jr```
  
## 3) Seed do banco subindo com a aplicação para testes no Postman:
- insert:
```POST localhost:8080/planets```
- findAll:
```GET localhost:8080/planets```
- findAllPaged:
```GET localhost:8080/planets?page=0&size=12&sort=name,asc```
- findByName:
```GET localhost:8080/planets/names?name=ho```
- findById:
```GET localhost:8080/planets/1```
- delete:
```DELETE localhost:8080/planets/3```

  
  
## 4) Testes de integração do Controller e Service.
- 14 testes realizados.

# Tecnologia utilizada

- Java
- Springboot
- Banco H2
- JSF + Primefaces

# Autor

Roberto Okamura

https://www.linkedin.com/in/roberto-okamura-6a9b59b4/
