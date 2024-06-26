package com.API.listaTareas.exception;

public class MensajeError {

    private String message;

    public MensajeError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
