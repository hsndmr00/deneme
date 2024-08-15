package com.example.garageWithSpring.type;

public class ErrorMessage extends Exception {
    private int statusCode = 500;
    public ErrorMessage(String message) {
        super(message);
    }

    public ErrorMessage(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
