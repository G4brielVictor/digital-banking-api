package application;

import model.Account;
import model.Transaction;
import model.User;

import service.AccountService;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        AccountService accountService = new AccountService();

        BankConsoleApp bank = new BankConsoleApp(userService, accountService);

        bank.start();
    }
}