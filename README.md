# 🏧 Caixa Eletrônico — POO UNICID 2026

> Projeto prático da disciplina de **Programação Orientada a Objetos** em Java.
> Universidade Cidade de São Paulo (UNICID) — 3º Semestre de 2026.
> Orientação: **Prof. Jadir Custódio Mendonça Junior**

---

## 📋 Descrição do Projeto

Simulação de um caixa eletrônico bancário desenvolvida em Java com interface gráfica (Java Swing). O sistema gerencia um estoque de cédulas de 6 tipos de notas (R$2, R$5, R$10, R$20, R$50 e R$100), permitindo saques automáticos com o menor número possível de notas, reposição de cédulas pelo administrador e geração de extrato ao encerrar a sessão.

---

## ✨ Funcionalidades

- **💳 Efetuar Saque** — Calcula automaticamente a combinação ideal de cédulas (algoritmo de backtracking priorizando notas de maior valor), com limite de 30 cédulas por operação.
- **📊 Relatório de Cédulas** — Exibe o estoque atual de cada tipo de nota.
- **💰 Valor Total Disponível** — Calcula e exibe o saldo total em caixa.
- **🔄 Reposição de Cédulas** — Permite ao administrador repor qualquer tipo de cédula.
- **⚙️ Cota Mínima** — Define o valor mínimo que o caixa deve manter; abaixo desse valor, o atendimento é suspenso com a mensagem "Caixa Vazio: Chame o Operador".
- **🚪 Sair com Extrato** — Ao encerrar, exibe o histórico completo de saques realizados na sessão.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 11+ | Linguagem principal |
| Java Swing | — | Interface gráfica |
| Java AWT | — | Layout e componentes visuais |
| Git / GitHub | — | Controle de versão |

---

## 🚀 Como Executar

### Opção 1 — Executar pelo JAR (mais simples)

```bash
java -jar CaixaEletronico/CaixaEletronico.jar
```

### Opção 2 — Importar no Eclipse

1. Clone o repositório:
   ```bash
   git clone https://github.com/wallacyti/POO-UNICID-2026
   ```
2. Abra o **Eclipse IDE**.
3. Vá em `File → Import → Existing Java Project`.
4. Selecione a pasta `CaixaEletronico/`.
5. Clique com botão direito no projeto → `Run As → Java Application`.
6. Selecione `CaixaEletronico` como classe principal.

### Pré-requisitos

- Java JDK 11 ou superior instalado
- Eclipse IDE (ou IntelliJ IDEA / VSCode com extensão Java)

---

## 🗂️ Estrutura do Projeto

```
POO-UNICID-2026/
└── CaixaEletronico/
    ├── src/
    │   └── caixaeletronico/
    │       ├── ICaixaEletronico.java  → Interface/contrato (não modificar)
    │       ├── CaixaEletronico.java   → Lógica principal do caixa
    │       └── GUI.java               → Interface gráfica Swing
    ├── CaixaEletronico.jar            → JAR executável
    ├── .classpath
    └── .project
```

---

## 🧠 Detalhes Técnicos

### Estoque inicial de cédulas

| Valor da Nota | Quantidade Inicial |
|---|---|
| R$ 100 | 100 |
| R$ 50 | 200 |
| R$ 20 | 300 |
| R$ 10 | 350 |
| R$ 5 | 450 |
| R$ 2 | 500 |

### Algoritmo de Saque (Backtracking Guloso)

O algoritmo começa pelas cédulas de maior valor e tenta alocar o máximo de notas possíveis antes de avançar para o próximo tipo. Respeita o limite de **30 cédulas por saque**. Caso não exista combinação possível, informa "Não Temos Notas Para Este Saque".

---

## 👥 Integrantes do Grupo

| # | Integrante | Responsabilidade |
|---|---|---|
| 1 | Benjamin | Documentação |
| 2 | Eder | Testes |
| 3 | Igor | Interface Gráfica |
| 4 | João | Lógica de Saque |
| 5 | Pedro Miguel | Reposição e Extrato |
| 6 | Wallacy Souza | Arquitetura e GitHub |

---
