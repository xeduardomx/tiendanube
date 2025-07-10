package com.tiendanube.eduardo.evaluacion.domain.exception;

public class LockNotAcquiredException extends RuntimeException {
    public LockNotAcquiredException(String message) {
        super(message);
    }
}
