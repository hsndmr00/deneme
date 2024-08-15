package com.example.garageWithSpring.type;

public class ResponseBodyMessage {
    private String message;

    public ResponseBodyMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
