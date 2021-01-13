package com.project.books.exception;

public class UserAlredyExists extends RuntimeException {
    public UserAlredyExists(String message) {
        super(message);
    }
}
