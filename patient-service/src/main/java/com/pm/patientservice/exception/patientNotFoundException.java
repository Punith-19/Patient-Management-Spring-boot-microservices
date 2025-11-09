package com.pm.patientservice.exception;

public class patientNotFoundException extends RuntimeException {
    public patientNotFoundException(String message) {
        super(message);
    }
}
