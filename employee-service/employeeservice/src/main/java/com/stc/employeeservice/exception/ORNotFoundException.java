package com.stc.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ORNotFoundException extends Exception  {
    private static final long serialVersionUID = 1L;
    public ORNotFoundException(String message){
        super(message);
    }
}
