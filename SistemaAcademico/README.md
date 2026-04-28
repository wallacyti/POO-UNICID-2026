# 🏦 Caixa Eletrônico + 🎓 Sistema Acadêmico
## Programação Orientada a Objetos — UNICID 2026

> Projetos desenvolvidos para a disciplina de POO em Java, sob orientação do **Prof. [cite_start]Jadir Custódio Mendonça Junior**. [cite: 1284-1285]

---

## 👥 Grupo
| # | Integrante | Responsabilidade |
|---|------------|------------------|
| 1 | Wallacy Souza | Arquitetura e GitHub |
| 2 | Nome Completo | Lógica de Saque |
| 3 | Nome Completo | Reposição e Extrato |
| 4 | Nome Completo | Banco de Dados |
| 5 | Nome Completo | Interface Gráfica |
| 6 | Nome Completo | Documentação e Testes |

---

## 🏧 Como Executar — Caixa Eletrônico
### Pré-requisitos
* Java JDK 11 ou superior [cite: 1296]
* [cite_start]Eclipse IDE (ou IntelliJ) [cite: 1297]
* [cite_start]Arquivo `GUI.jar` (fornecido pelo professor) [cite: 1298]

### Passos para Instalação
1. Clone o repositório:  
   [cite_start]`git clone https://github.com/wallacyti/POO-UNICID-2026` [cite: 1300]
2. [cite_start]Abra o Eclipse e vá em `File > Import > Existing Java Project`. [cite: 1301]
3. [cite_start]Selecione a pasta `CaixaEletronico/`. [cite: 1302]
4. [cite_start]Clique com o botão direito no projeto > `Build Path > Add External JARs` > selecione o arquivo `GUI.jar`. [cite: 1303]
5. [cite_start]Execute o arquivo `CaixaEletronico.java`. [cite: 1304]

---

## 🏫 Como Executar — Sistema Acadêmico
### Pré-requisitos
* [cite_start]Java JDK 11 ou superior [cite: 1308]
* MySQL 8.0 instalado e rodando [cite: 1309]
* [cite_start]MySQL Connector/J (driver JDBC) [cite: 1310]

### Passos para Instalação
1. [cite_start]Execute o script SQL localizado em `sql/banco.sql` no seu MySQL Workbench. [cite: 1312]
2. [cite_start]Edite o arquivo `src/dao/Conexao.java` e altere a variável `SENHA` para a sua senha do root do MySQL. [cite: 1313]
3. [cite_start]Vá em `Build Path > Add External JARs` e selecione o arquivo `mysql-connector-j-X.jar`. [cite: 1314]
4. [cite_start]Execute a classe `view/TelaPrincipal.java`. [cite: 1314]

---

## 🛠️ Tecnologias Utilizadas
* [cite_start]**Linguagem:** Java 11+ [cite: 1317]
* **Interface:** Java Swing [cite: 1319]
* [cite_start]**Banco de Dados:** MySQL 8.0 [cite: 1319]
* [cite_start]**Conectividade:** JDBC [cite: 1319]
* **Versão:** Git & GitHub [cite: 1319]

---

## 🚀 Funcionalidades Implementadas

### Caixa Eletrônico
- [x] Saque priorizando notas maiores (Algoritmo Guloso) [cite: 1322]
- [x] Limite de segurança de no máximo 30 cédulas por saque [cite: 1323]
- [x] Relatório detalhado de cédulas em estoque [cite: 1324]
- [x] Sistema de reposição de cédulas pelo administrador [cite: 1325]
- [x] Configuração de cota mínima para operação [cite: 1326]
- [x] Geração de extrato detalhado ao encerrar a sessão [cite: 1327]

### Sistema Acadêmico
- [x] Cadastro de alunos com validação de RGM único [cite: 1329]
- [x] Máscaras de entrada para CPF, Celular e Data (JFormattedTextField) [cite: 1330]
- [x] Interface organizada em 4 abas (JTabbedPane) [cite: 1331]
- [x] CRUD completo (Criar, Ler, Atualizar, Deletar) integrado ao MySQL [cite: 1332]
- [x] Exclusão em cascata (deleta notas e faltas ao remover um aluno) [cite: 1333]