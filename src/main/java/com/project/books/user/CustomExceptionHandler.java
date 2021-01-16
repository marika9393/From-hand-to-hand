package com.project.books.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // String -> obiekt
    public String handle(MethodArgumentNotValidException exp) {
        return exp.getMessage();
    }

    @ExceptionHandler(Exception.class)
    // String -> obiekt
    public String handle(Exception exp) {
        return "Sth went wrong: " + exp.getMessage();
    }
}
