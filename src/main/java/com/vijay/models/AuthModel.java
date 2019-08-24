package com.vijay.models;

public class AuthModel {

    private String jwt;

    private boolean success;

    private String message;

    public AuthModel(String jwt, boolean success, String message) {
        this.jwt = jwt;
        this.success = success;
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
