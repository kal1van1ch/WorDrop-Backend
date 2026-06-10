package com.kal1van1ch.wordrop.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String mes){
        super(mes);
    }
}
