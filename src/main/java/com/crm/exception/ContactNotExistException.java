package com.crm.exception;

public class ContactNotExistException extends RuntimeException {
    public ContactNotExistException(String msg) {
        super(msg);
    }
}
