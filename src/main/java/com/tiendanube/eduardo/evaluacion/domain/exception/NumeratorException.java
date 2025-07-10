package com.tiendanube.eduardo.evaluacion.domain.exception;

public class NumeratorException extends RuntimeException {
    public NumeratorException(String message) {
        super(message);
    }

    public NumeratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
