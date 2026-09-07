package service;

import exceptions.SameAccountTransferException;
import model.Account;

public class AccountService {

    public void transfer(Account accountFrom, Account accountTo, Double amount){
        if(accountFrom.getId().equals(accountTo.getId())){
            throw new SameAccountTransferException("A conta de origem e destino não podem ser iguais.");
        }

        if(amount <= 0){
            throw new IllegalArgumentException("Valor inválido para transferencia");
        }

        accountFrom.withdraw(amount);
        accountTo.deposit(amount);
    }
}
