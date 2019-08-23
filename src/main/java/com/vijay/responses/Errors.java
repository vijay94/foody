package com.vijay.responses;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class Errors {

    private Map<String, String> messages;

    public Errors() {
    }

    public Errors(String msg) {
        this.messages = new HashMap<>();
        this.messages.put("message", msg);
    }

    public Errors(BindingResult bindingResult) {
        org.springframework.util.StringUtils.isEmpty("null");
        this.messages = new HashMap<>();
        for (FieldError fieldError: bindingResult.getFieldErrors())
            messages.put(fieldError.getField(), fieldError.getDefaultMessage());
    }

    public Map getMessages() {
        return messages;
    }

    public void setMessages(Map messages) {
        this.messages = messages;
    }

}
