package com.kal1van1ch.wordrop.exception;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String mes){
        super(mes);
    }
}
