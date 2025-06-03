# Alertus Java

Este diretório contém um exemplo simples de aplicação web em Java utilizando JSP, Servlets e JDBC. O projeto segue o padrão MVC e foi desenvolvido para fins acadêmicos.

## Estrutura
- `src/` – código Java dividido em `model`, `dao` e `controller`.
- `WebContent/view/` – páginas JSP.
- `WebContent/WEB-INF/web.xml` – configurações da aplicação.
- `db/alertus.sql` – script para criação do banco MySQL.

## Execução
1. Importe o projeto em um servidor como Apache Tomcat.
2. Execute o script `db/alertus.sql` em um banco MySQL local.
3. Ajuste as credenciais de acesso em `dao/DBConnection.java` se necessário.
4. Acesse `http://localhost:8080/alertus-java/login.jsp` para fazer login (usuário padrão `admin`/`admin`).
