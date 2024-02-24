package com.crm.exception;

public class LeadNotExistException extends RuntimeException{

    public LeadNotExistException(String msg){
        super(msg);
    }
}
