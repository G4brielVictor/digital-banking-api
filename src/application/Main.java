package application;

import model.Account;
import model.Transaction;
import model.User;

import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UserService userService = new UserService();

        try {
            System.out.println("==== Bank of Santander ====\n");
            System.out.println("Create account System\n");

            System.out.print("Enter the name: ");
            var name = sc.nextLine();

            System.out.print("Enter the CPF: ");
            var cpf = sc.next();

            System.out.print("Enter the Email: ");
            var email = sc.next();

            System.out.print("Enter the Password: ");
            var password = sc.next();

            User registeredUser = userService.registerUser(name, cpf, email, password);

            System.out.println("User registered successfully! Welcome " + registeredUser.getName());
        }
        catch (RuntimeException e) {
            System.out.println("\nErro: " + e.getMessage());
        }

        try {
            System.out.println("==== Login System ====\n");

            System.out.print("Enter the CPF or Email: ");
            var credentials = sc.next();

            System.out.print("Enter the Password: ");
            var password = sc.next();

            User userLogged = userService.loginUser(credentials, password);

            System.out.println("Login successfully! Welcome " + userLogged.getName());
            for(Account account : userLogged.getAccounts()) {
                System.out.println(account.getTransactions());
            }
        }
        catch (RuntimeException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }
}