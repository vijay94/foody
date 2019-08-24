package com.vijay.exception;

import com.vijay.responses.Errors;
import com.vijay.responses.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FoodyExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity inValidRequestException(InvalidRequestException exception){
        return new ResponseEntity<>(new GenericResponse<>(400, new Errors(exception.getBindingResult()), exception.getMessage()), null , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    protected ResponseEntity unAuthorizedUser(UnAuthorizedException exception){
        return new ResponseEntity<>(new GenericResponse<>(401, new Errors(exception.getMessage()), exception.getMessage()), null , HttpStatus.UNAUTHORIZED);
    }

}