package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    //Informações do usuário, nome, id, cpf e etc.

    private final UUID id;
    private String name;
    private final String cpf;
    private String email;
    private final String password;

    //A ideia é criar um método para que um usuário possa ter conta ou contas

    private final List<Account> accounts = new ArrayList<>()  ;

    //Validação necessário, pois o usuário não pode ser instanciao com informacoes nulas ou vazias.
    public User(String name, String cpf, String email, String password) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Nome não pode ser vazio.");
            //Vou retornar uma msg apenas informando que o nome está vazio.
        }

        if(cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("CPF não pode ser vazio.");
            //Vou retornar uma msg apenas informando que o cpf está vazio.
        }

        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Email não pode ser vazio.");
        }

        if(password == null || password.isBlank()){
            throw new IllegalArgumentException("A senha não pode ser vazia.");
        }

        this.id = UUID.randomUUID();
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

}
