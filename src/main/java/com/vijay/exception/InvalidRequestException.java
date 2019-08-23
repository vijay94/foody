package com.vijay.exception;

import org.springframework.validation.BindingResult;

public class InvalidRequestException extends Throwable {

    private BindingResult bindingResult;

    public InvalidRequestException(BindingResult bindingResult) {
        super("Invalid Request body");
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
