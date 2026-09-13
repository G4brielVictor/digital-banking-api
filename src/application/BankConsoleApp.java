package application;

import model.*;
import service.AccountService;
import service.UserService;

import java.util.Scanner;

public class BankConsoleApp {

        private final Scanner sc;
        private final UserService userService;
        private final AccountService accountService;
        private User loggedUser;

        public BankConsoleApp(UserService userService, AccountService accountService) {
            this.userService = userService;
            this.accountService = accountService;
            sc = new Scanner(System.in);
        }

        //Aqui é o método de controle do fluxo
        public void start() {

            System.out.println("==========================");
            System.out.println("      SANTANDER BANK      ");
            System.out.println("==========================");

            while(true) {
                try {
                    if(loggedUser == null) {
                        boolean running = showMainMenu();
                        if(!running) break;
                    }

                    else {
                        boolean running = showUserMenu();
                        if(!running) {
                            this.loggedUser = null;
                            System.out.println("Logout realizado com sucesso");
                        }
                    }
                }
                catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
            sc.close();
        }

        //Aqui são os métodos de exibição de tela
        private boolean showMainMenu() {
            System.out.println("===== Register Menu =====\n");

            System.out.println("1 - Cadastrar Novo usuário");
            System.out.println("2 - Fazer login");
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opcao: ");
            int op = sc.nextInt();

            sc.nextLine();
            switch (op) {
                case 1:
                    handleRegister();
                    break;
                case 2:
                    handleLogin();
                    break;
                case 0:
                    System.out.println("Saindo do banco...");
                    return false;
                default:
                    System.out.println("Opção inválida, tente novamente");
            }
            return true;
        }

        private boolean showUserMenu() {
            System.out.println("==========================");
            System.out.printf("     BEM-VINDO, %s         ", loggedUser.getName());
            System.out.println("==========================");

            if(loggedUser.getAccounts().isEmpty()) {
                System.out.println("1 - Criar Conta Bancária");
                System.out.println("2 - Verificar dados do usuário");
                System.out.println("0 - Logout (Sair da conta)");

                int op = sc.nextInt();

                sc.nextLine();
                switch (op) {
                    case 1:
                        handleCreateAccount();
                        break;
                    case 2:
                        System.out.println(loggedUser);
                        break;
                    case 0:
                        System.out.println("Saindo da conta...");
                        return false;
                    default:
                        System.out.println("Opção inválida, tente novamente");
                }
            }

            else {
                Account account = loggedUser.getAccounts().getFirst();
                System.out.printf("Saldo %.2f\n", account.getBalance());

                System.out.println("1 - Realizar Depósito");
                System.out.println("2 - Realizar Saque");
                System.out.println("3 - Realizar Transferencia");
                System.out.println("4 - Ver extrato completo");
                System.out.println("0 - Logout (Sair da conta)");

                System.out.print("Escolha uma opcao: ");
                int op = sc.nextInt();

                sc.nextLine();
                switch (op) {
                    case 1:
                        handleDeposit();
                        break;
                    case 2:
                        handleWithdraw();
                        break;
                    case 3:
                        handleTransfer();
                        break;
                    case 4:
                        handleStatement();
                        break;
                    case 0:
                        return false;
                    default:
                        System.out.println("Opção inválida, tente novamente");
                }
            }
            return true;
        }

        //Aqui são os métodos de ação de Úsuario
        private void handleRegister(){
            System.out.print("Entre com o CPF: ");
            String cpf = sc.nextLine();

            System.out.print("Entre com seu nome: ");
            String name = sc.nextLine();

            System.out.print("Entre com o Email: ");
            String email = sc.nextLine();

            System.out.print("Digite sua senha (Min 8 dígitos): ");
            String password = sc.nextLine();

            userService.registerUser(name, cpf, email, password);
        }

        private void handleLogin(){
            System.out.print("Entre com o CPF ou Email: ");
            String identifier = sc.nextLine();

            System.out.print("Digite a senha: ");
            String password = sc.nextLine();

            this.loggedUser = userService.loginUser(identifier, password);
        }

        //Métodos de ações bancárias
        private void handleCreateAccount(){
            System.out.print("Deseja realizar um depósito inicial? ");
            System.out.println("S - Sim | N - Não");

            char op = sc.next().charAt(0);
            op = Character.toUpperCase(op);

            sc.nextLine();
            if(op == 'S'){
                System.out.print("Entre com o valor de depósito: ");
                double deposit = sc.nextDouble();

                accountService.createAccount(loggedUser, deposit);
            }
            else {
                accountService.createAccount(loggedUser, 0.0);
            }
        }

        private void handleDeposit(){
            System.out.print("Entre com o valor do depósito: ");
            double deposit = sc.nextDouble();

            sc.nextLine();
            loggedUser.getAccounts().getFirst().deposit(deposit);
        }

        private void handleWithdraw(){
            System.out.print("Entre com o valor do saque: ");
            double withdraw = sc.nextDouble();

            sc.nextLine();
            loggedUser.getAccounts().getFirst().withdraw(withdraw);
        }

        private void handleTransfer(){
            System.out.println("Para quem deseja realizar a transferencia? ");
            System.out.print("Digite o CPF: ");
            String cpf = sc.nextLine();

            User destinationUser = userService.findByCpf(cpf);

            Account accountFrom = loggedUser.getAccounts().getFirst();
            Account accountTo = destinationUser.getAccounts().getFirst();

            System.out.printf("Qual o valor da transferencia para %s? ", accountTo.getOwner().getName());
            double transfer = sc.nextDouble();

            accountService.transfer(accountFrom, accountTo, transfer);
        }

        private void handleStatement(){
            System.out.println("===== Extrato completo =====\n");

            Account account  = loggedUser.getAccounts().getFirst();
            account.printStatement();
        }
}
