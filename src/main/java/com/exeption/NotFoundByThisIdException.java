package com.exeption;

public class NotFoundByThisIdException extends Exception {
    public NotFoundByThisIdException(String message) {
        super(message);
    }
}
