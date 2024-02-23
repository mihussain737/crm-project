package com.crm.exception;

public class LeadAlreadyExistsException extends RuntimeException{
    public LeadAlreadyExistsException(String msg){
        super(msg);
    }
}
