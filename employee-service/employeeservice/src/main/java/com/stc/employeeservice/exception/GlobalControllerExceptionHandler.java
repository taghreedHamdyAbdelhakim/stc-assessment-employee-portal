package com.stc.employeeservice.exception;


import com.stc.employeeservice.dtos.ORErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
@Hidden

public class GlobalControllerExceptionHandler   {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ORErrorResponse> handleInternalErrors(RuntimeException ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ORErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Internal Error"));
    }

    @ExceptionHandler(ORNotFoundException.class)
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    public ResponseEntity<ORErrorResponse> handleNotFound(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ORErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage()));

    }

    @ExceptionHandler({ORBadRequestException.class })
    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    public ResponseEntity<ORErrorResponse> handleBadRequest(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ORErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage()));

    }


    @ExceptionHandler( { HttpMessageNotReadableException.class})
    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    public ResponseEntity<ORErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ORErrorResponse(HttpStatus.BAD_REQUEST.value(),"Invalid body"));
    }

}
