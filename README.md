# 🏦 Digital Banking Core Engine

> A banking engine developed in **pure Java 21**, focused on Object-Oriented Programming, basic Domain-Driven Design (DDD) concepts, strict encapsulation, domain rules, and a clean layered architecture.

---

## 📌 About the Project

**Digital Banking Core Engine** is a backend-oriented banking system designed to manage users, bank accounts, and financial transactions.

The project was built without frameworks, prioritizing a solid understanding of Java fundamentals, domain modeling, business rules, exception handling, and code organization.

The application also provides an interactive console interface for user authentication and banking operations.

---

## 🛠️ Technologies and Concepts

- **Language:** Java 21 (LTS)
- **Paradigm:** Object-Oriented Programming (OOP)
- **Domain Modeling:** Rich Domain Model concepts
- **Date & Time API:** `java.time.LocalDateTime` and `DateTimeFormatter`
- **Version Control:** Git
- **Commit Convention:** Conventional Commits
- **Architecture:** Separation of responsibilities into layers:
  - `model`
  - `service`
  - `application`
  - `exceptions`

---

## 🏛️ Architecture and Design Decisions

### 1. Encapsulation and Invariant Protection

The `User` and `Account` entities validate their data during object creation, preventing objects from being created in an inconsistent or invalid state.

### 2. Immutability and Transaction Integrity

The `Transaction` class uses immutable attributes and does not expose setters, ensuring that transaction records cannot be modified after creation.

### 3. DRY and Method Overloading

The `Account` class uses method overloading for `withdraw` and `deposit`, allowing different transaction contexts, such as `TRANSFER_SENT` and `TRANSFER_RECEIVED`, without duplicating balance and limit validation rules.

### 4. Semantic Error Handling

The application uses specific business exceptions extending `RuntimeException` to represent invalid operations and domain rule violations.

This approach allows errors to be handled according to their business meaning instead of relying on ambiguous return values such as `null` or `boolean`.

---

## 💼 Implemented Business Rules

### Users and Authentication

- User registration with CPF validation using 11 numeric digits.
- Email format validation.
- Strong password validation:
  - Minimum of 8 characters.
  - At least one uppercase letter.
  - At least one number.
  - At least one special character.
- CPF uniqueness validation.
- Email uniqueness validation.
- Authentication using either CPF or email combined with a password.

### Bank Accounts

- Automatic sequential account number generation.
- Operational withdrawal and deposit limits per transaction.
- Support for an initial deposit when opening an account.
- Transaction registration for account operations.

### Financial Operations

- Deposits with value and operational limit validation.
- Withdrawals with balance and operational limit validation.
- Transfers between accounts using the recipient's CPF as an identifier.
- Detailed transaction history.
- Date and time formatting using:

```text
dd/MM/yyyy HH:mm:ss
