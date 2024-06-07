# Web Scraping em Kotlin

Este é um projeto Kotlin que realiza web scraping para coletar informações de um site específico e armazená-las em um banco de dados. Ele usa o framework Spring Boot para criar uma API que permite acessar e manipular os dados coletados.

## Tecnologias Utilizadas

- Kotlin
- Spring Boot
- Spring Data JPA
- Hibernate
- Jsoup

## Funcionalidades

- Web scraping para coletar informações sobre livros de um site chamado PNDL Moderna.
- Armazenamento dos dados coletados em um banco de dados.
- API REST para acessar os dados coletados.

## Configuração do Projeto

1. **Clonando o Repositório:**
   ```bash
   git clone https://github.com/Nicholas-Cardoso/Web-Scraping.git
   ```

2. **Configurando o Banco de Dados:**
   - Configure as credenciais do banco de dados no arquivo `application.yml`.

3. **Executando o Projeto:**
   ```bash
   ./gradlew bootRun
   ```

## Uso da API

- **GET /web-scraping**: Retorna todas as informações coletadas pelo web scraping.

## Monitoramento

A aplicação possui monitoramento integrado utilizando o Scheduled, com a finalidade de buscar os dados mais recentes no site e assim salvar no banco de dados.
Este monitoramento ocorre de 1 em 1 minuto (60000 ms).

## Contribuindo

Sinta-se à vontade para abrir uma issue ou enviar um pull request com melhorias ou correções.

## Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).
