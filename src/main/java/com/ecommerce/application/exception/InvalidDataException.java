package com.ecommerce.application.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String mensaje) {
        super(mensaje);
    }
}
