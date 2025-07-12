package com.seuprojeto.todolist.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailEntity(EmailAlreadyExistsException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericExpection(Exception e){
        return  ResponseEntity.status(500).body("Erro interno no servidor.");
    }

}
