package service;

import exceptions.SameAccountTransferException;
import model.Account;
import model.Transaction;
import model.enums.TransactionType;

public class AccountService {

    public void transfer(Account accountFrom, Account accountTo, Double amount){
        if(accountFrom.getId().equals(accountTo.getId())){
            throw new SameAccountTransferException("A conta de origem e destino não podem ser iguais.");
        }

        if(amount <= 0){
            throw new IllegalArgumentException("Valor inválido para transferencia");
        }

        Transaction transaction;

        accountFrom.withdraw(amount);
        new Transaction(TransactionType.TRANSFER_SENT, amount, "Transferencia realizada com sucesso");

        accountTo.deposit(amount);
        new Transaction(TransactionType.TRANSFER_RECEIVED, amount, "Transferencia recebida com sucesso");
    }
}
