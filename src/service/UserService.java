package service;

import exceptions.AccountNotFoundException;
import exceptions.UserNotFoundException;
import exceptions.InvalidPasswordException;
import exceptions.UserAlreadyExistsException;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    //Aqui fiz uma lista de usuarios para eu poder acessa-los e poder adicionar um novo usuario, comparar etc.
    private final List<User> users = new ArrayList<>();

    public User registerUser(String name, String cpf, String email, String password){

        /*Breve validação para verificar se cpf está correto ou se o email está utilizando o dominio e a
        Separaçao de forma correta*/

        //Validation CPF
        if(!cpf.matches("[0-9]{11}")){
            throw new IllegalArgumentException("CPF deve conter 11 digitos");
        }

        //Validation email
        if(!email.contains("@") || !email.contains(".")){
            throw new IllegalArgumentException("Email deve conter um dominio");
        }

        //Validation Password
        if(password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*") || !password.matches(".*[@#$%&*].*")) {
            throw new InvalidPasswordException("A senha deve conter no mínimo 1 caracter especial (@#$&*), 1 letra maiuscula e um número");
        }

        //For para percorrer entre as contas já cadastradas no sistema
        for(User u : users){
            //Verificação se o CPF ou Email já foi cadastrado no sistema.
            if(u.getCpf().equals(cpf)){
                throw new UserAlreadyExistsException("CPF já cadastrado no sistema");
            }

            if(u.getEmail().equals(email)){
                throw new UserAlreadyExistsException("Email já cadastrado no sistema");
            }
        }

        //Instanciando a classe user, adicionando na lista e retornando o objeto.
        User registeredUser = new User(name, cpf, email, password);
        this.users.add(registeredUser);
        return registeredUser;
    }

    public User loginUser(String identifier, String password){

        /*Percorrendo os usuários cadastrados para validar as informações,
        se a senha não for igual ou se a conta não for encontrada, é lançado uma exceção.*/

        for(User u : users){
            if(u.getCpf().equals(identifier) || u.getEmail().equals(identifier)){
                if(u.getPassword().equals(password)){
                    return u;
                }
                else {
                    throw new InvalidPasswordException("Senha incorreta");
                }
            }
        }

        throw new UserNotFoundException("Usuário não encontrado");
    }

    public User findByCpf(String identifier){
        for(User u : users){
            if(u.getCpf().equals(identifier)){
                if(!u.getAccounts().isEmpty()){
                    return u;
                }
                else {
                    throw new AccountNotFoundException("O usuário destinatário não possui conta bancária ativa.");
                }
            }
        }

        throw new UserNotFoundException("Destinatário não encontrado com o CPF informado.");
    }
}
