package com.tiendanube.eduardo.evaluacion.domain.model;

import java.time.Instant;

public class ApiErrorResponse {
    private final int status;
    private final String error;
    private final String message;
    private final String timestamp;

    public ApiErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = Instant.now().toString();
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}