package com.vijay.responses;

public class GenericResponse<T> {

    private int statusCode;

    private String message;

    private T data;

    public GenericResponse() {
    }

    public GenericResponse(int statusCode, T data, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public GenericResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
