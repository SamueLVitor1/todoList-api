package com.seuprojeto.todolist.exception;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException() {
        super("Credenciais inválidas.");
    }
}
