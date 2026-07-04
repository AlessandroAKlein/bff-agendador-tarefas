package com.javanauta.bffagendador.infrastructure.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String mensagem, Throwable throwable){
        super(mensagem);
    }

}
