package com.tiendanube.eduardo.evaluacion.domain.exception;

public class ReceivableAlreadyExistsException extends RuntimeException {
    public ReceivableAlreadyExistsException(String message) {
        super(message);
    }

    public ReceivableAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
