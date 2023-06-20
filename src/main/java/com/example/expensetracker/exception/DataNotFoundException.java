package com.example.expensetracker.exception;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String message, Throwable t){
        super(message,t);
    }

    public DataNotFoundException(String message){
        super(message);
    }

}
