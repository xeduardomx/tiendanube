package com.tiendanube.eduardo.evaluacion.infrastructure.external.numerator.dto;

public class LockRequest {
    private int timeout;

    public LockRequest(int timeout) {
        this.timeout = timeout;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
