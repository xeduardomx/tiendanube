package com.tiendanube.eduardo.evaluacion.domain.exception;

public class ReceivableException extends RuntimeException {
    public ReceivableException(String message) {
        super(message);
    }

    public ReceivableException(String message, Throwable cause) {
        super(message, cause);
    }
}

