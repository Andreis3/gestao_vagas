package br.com.andreisantos.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException(String msg) {
        super(msg);
    }
}
