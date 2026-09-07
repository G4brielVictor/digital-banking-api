package model;

import exceptions.DepositLimitExceededException;
import exceptions.InsufficientBalanceException;
import exceptions.WithdrawLimitExceededException;

import java.util.UUID;

public class Account {

    //Todas as informações da conta, ID, número da conta e etc.

    private final UUID id;
    private Integer accountNumber;
    private Double balance;
    private Double withdrawLimit;
    private Double depositLimit;
    private User owner;

    public Account(User owner, Integer accountNumber, Double balance, Double withdrawLimit, Double depositLimit) {
        this.id = UUID.randomUUID();
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
        this.depositLimit = depositLimit;
    }

    public UUID getId() {
        return id;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public Double getWithdrawLimit() {
        return withdrawLimit;
    }

    public Double getDepositLimit() {
        return depositLimit;
    }

    public User getOwner() {
        return owner;
    }

    //Métodos de négocio. A conta pode realizar algumas alteraçoes, como saque, deposito. Ainda irei adicionar mais alguns métodos.
    public void withdraw(double amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("Erro no saque realizado.");
        }

        if(amount > withdrawLimit) {
            throw new WithdrawLimitExceededException("Excede o limite permitido por operação");
        }

        if(amount > balance) {
            throw new InsufficientBalanceException("Saldo insuficiente para realizar o saque");
        }

        this.balance -= amount;
    }

    public void deposit(double amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("Erro no deposito realizado.");
        }

        if(amount > depositLimit) {
            throw new DepositLimitExceededException("Deposito acima do limite de deposito");
        }

        this.balance += amount;
    }

}
