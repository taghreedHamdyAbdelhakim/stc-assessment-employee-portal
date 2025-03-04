package com.stc.employeeservice.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class ORBadRequestException extends RuntimeException {
    private static final long serialVersionUID = -6958128182491079251L;

    public ORBadRequestException(String message) {
        super(message);
    }
}