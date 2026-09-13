package service;

import exceptions.SameAccountTransferException;
import model.Account;
import model.User;
import model.enums.TransactionType;

public class AccountService {

    private int nextAccountNumber = 1000;

    public Account createAccount(User user, Double initialDeposit){
        if(user == null){
            throw new IllegalArgumentException("A conta não pode ser nula.");
        }
        
        if(initialDeposit == null){
            initialDeposit = 0.0;
        }

        int accountNumber = nextAccountNumber++;

        Account account = new Account(user, accountNumber, 0.0, 1000.00, 2000.00);

        if(initialDeposit > 0){
            account.deposit(initialDeposit);    
        }

        user.addAccount(account);
        return account;
    }

    /*Optei por usar o AccountService para manter o Princípio da Responsabilidade Única (SRP)
    e evitar o acoplamento direto entre entidades. Uma conta não deve conhecer nem manipular diretamente os detalhes de outra conta. O
    AccountService atua como uma camada de orquestração de domínio, garantindo que a operação de débito e crédito ocorra como uma transação coesa e atômica."*/
    public void transfer(Account accountFrom, Account accountTo, Double amount){
        if(accountFrom.getId().equals(accountTo.getId())){
            throw new SameAccountTransferException("A conta de origem e destino não podem ser iguais.");
        }

        if(amount <= 0){
            throw new IllegalArgumentException("Valor inválido para transferencia");
        }

        accountFrom.withdraw(amount, TransactionType.TRANSFER_SENT, "Transferencia realizada com sucesso");
        accountTo.deposit(amount, TransactionType.TRANSFER_RECEIVED, "Transferencia recebida com sucesso");
    }
}
