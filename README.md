# 📁 Caixa Eletronico + 📁 Sistema Academico
## Programacao Orientada a Objetos — UNICID 2026
> Projetos desenvolvidos para a disciplina de POO em Java,
> Prof. Jadir Custodio Mendonca Junior
---
## 📁 Grupo
| # | Integrante | Responsabilidade |
|---|-----------|-----------------|
| 1 | Wallacy Souza | Arquitetura e GitHub |
| 2 | Nome Completo | Logica de Saque |
| 3 | Nome Completo | Reposicao e Extrato |
| 4 | Nome Completo | Banco de Dados |
| 5 | Nome Completo | Interface Grafica |
| 6 | Nome Completo | Documentacao e Testes |
---
## 📁 Como Executar — Caixa Eletronico
### Pre-requisitos
- Java JDK 11 ou superior
- Eclipse IDE (ou IntelliJ)
- Arquivo GUI.jar (fornecido pelo professor)
### Passos
1. Clone: `git clone https://github.com/USUARIO/POO-UNICID-2026`
2. Abra o Eclipse > File > Import > Existing Java Project
3. Selecione a pasta `CaixaEletronico/`
4. Build Path > Add External JARs > selecione `GUI.jar`
5. Execute `CaixaEletronico.java`
---
## 📁 Como Executar — Sistema Academico
### Pre-requisitos
- Java JDK 11 ou superior
- MySQL 8.0 instalado e rodando
- MySQL Connector/J (driver JDBC)
### Passos
1. Execute o script SQL: `sql/banco.sql` no MySQL Workbench
2. Edite `src/dao/Conexao.java`: altere SENHA para sua senha root
3. Build Path > Add External JARs > selecione `mysql-connector-j-X.jar`
4. Execute `view/TelaPrincipal.java`
---
## 📁 Tecnologias
- Java 11+ | Java Swing | MySQL 8.0 | JDBC | Git
## 📁 Funcionalidades
### Caixa Eletronico
- [x] Saque priorizando notas maiores
- [x] Limite de 30 cedulas por saque
- [x] Relatorio de cedulas
- [x] Reposicao de cedulas
- [x] Cota minima configuravel
- [x] Extrato ao sair
### Sistema Academico
- [x] Cadastro com RGM unico
- [x] Campos formatados (CPF, Celular, Data)
- [x] 4 abas com JTabbedPane
- [x] CRUD completo com MySQL
- [x] Exclusao em cascata
