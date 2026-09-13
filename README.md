# 🏦 Digital Banking Core Engine

> Motor bancário desenvolvido em **Java 21 puro**, focado em boas práticas de Orientação a Objetos, Domain-Driven Design (DDD) básico, encapsulamento rigoroso e arquitetura limpa em camadas.

---

## 📌 Sobre o Projeto

O **Digital Banking Core Engine** é uma simulação de backend bancário projetada para gerenciar o ciclo de vida completo de clientes, contas e transações financeiras. 

O projeto foi construído sem o uso de frameworks mágicos, priorizando o domínio dos fundamentos da linguagem Java, imutabilidade de fatos contábeis, tratamento robusto de exceções de domínio e uma interface interativa de console com controle de sessão.

---

## 🛠️ Tecnologias e Recursos Utilizados

- **Linguagem:** Java 21 (LTS)
- **Paradigma:** Orientação a Objetos (POO) com Modelo Rico (*Rich Domain Model*)
- **Date & Time API:** `java.time.LocalDateTime` e `DateTimeFormatter`
- **Controle de Versão:** Git com padrão *Conventional Commits*
- **Arquitetura:** Separação de responsabilidades em camadas (`model`, `service`, `application`, `exceptions`)

---

## 🏛️ Arquitetura e Decisões de Design
### Principais Decisões Técnicas:
1. **Encapsulamento e Proteção de Invariantes:**
   - As entidades `User` e `Account` validam seus dados diretamente nos construtores, impedindo que objetos nasçam em estado inconsistente ou com atributos nulos/inválidos.
2. **Imutabilidade e Integridade Contábil:**
   - A classe `Transaction` possui atributos imutáveis (`final`) e não expõe métodos setters, garantindo que o histórico financeiro seja um fato auditável e inviolável.
3. **Princípio DRY e Sobrecarga de Métodos:**
   - A classe `Account` utiliza sobrecarga (*Method Overloading*) nos métodos `withdraw` e `deposit`, permitindo que transferências gerem comprovantes customizados (`TRANSFER_SENT` e `TRANSFER_RECEIVED`) sem duplicar regras de saldo e limites.
4. **Tratamento Semântico de Erros (Fail-Fast):**
   - Criação de exceções de negócio específicas estendendo `RuntimeException`, evitando retornos nulos ou booleanos e propagando mensagens de erro expressivas para a interface.

---

## 💼 Regras de Negócio Implementadas

- **Usuários & Autenticação:**
  - Cadastro com validação de CPF (11 dígitos numéricos) e formato de e-mail.
  - Validação de senha forte (mínimo de 8 caracteres, letra maiúscula, número e caractere especial).
  - Verificação de unicidade de CPF e e-mail.
  - Autenticação híbrida (login via CPF ou E-mail + Senha).
- **Contas Bancárias:**
  - Geração sequencial e automática de número de conta.
  - Limite operacional de saque e depósito por transação.
  - Suporte a depósito inicial de abertura com emissão de extrato.
- **Operações Financeiras:**
  - Depósito e Saque com validação de saldo e limites operacionais.
  - Transferência entre contas utilizando o CPF do destinatário ("Chave Pix").
  - Extrato bancário detalhado com carimbo de data/hora (`dd/MM/yyyy HH:mm:ss`) e valor formatado em moeda brasileira (`R$`).

---

## 🚀 Como Executar o Projeto

### Pré-requisitos:
- Java JDK 21 ou superior instalado.
- Git instalado.

### Passo a passo:

1.*Clone o repositório:**
   ```bash
   git clone https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git
   ```
2.**Acesse o diretório do projeto:**
  ```bash
  cd "Digital Bank API"
  ```
3.**Compile os arquivos Java:**
  ```bash
  javac -d bin src/model/enums/*.java src/model/*.java src/exceptions/*.java src/service/*.java src/application/*.java
  ```
4.**Execute a aplicação:**
  ```bash
  java -cp bin application.Main
   ```
   
---
   
##🗺️ Próximos Passos (Roadmap de Evolução)
•
[ ] Refatoração para a camada Repository (separando persistência de regras de negócio).
•
[ ] Implementação de AccountType com contas Corrente, Poupança e Universitária.
•
[ ] Escrita de testes unitários automatizados com JUnit 5.
•
[ ] Migração para Spring Boot (Spring Web, Spring Data JPA, PostgreSQL e Docker).

##👨‍💻 Autor
Desenvolvido por Gabriel Victor.
Estudante e entusiasta do ecossistema Java e Engenharia de Software Backend.

---

### DICAS PARA SUBIR NO GITHUB:

1. Crie um arquivo chamado **`README.md`** na raiz da sua pasta `Digital Bank API`.
2. Cole o conteúdo acima.
3. Altere o link do `git clone` com o seu usuário real do GitHub.
4. Faça o commit e push:
   ```bash
   git add README.md
   git commit -m "docs: add comprehensive project README with architecture and domain decisions"
   git push
