package com.tiendanube.eduardo.evaluacion.domain.exception;

import com.tiendanube.eduardo.evaluacion.domain.model.ApiErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class GlobalExceptionHandler {
    //TODO:
    // Se usan solo códigos HTTP estándar con mensajes claros.
    // No se manejan códigos de error específicos de negocio para evitar complejidad.
    // En futuras versiones se podrá considerar su implementación para mejorar el manejo de errores.

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(LockNotAcquiredException.class)
    public ResponseEntity<ApiErrorResponse> handleLockNotAcquired(LockNotAcquiredException ex) {
        logger.error("Lock could not be acquired: ", ex);
        ApiErrorResponse response = new ApiErrorResponse(
                423,
                "Lock Not Acquired",
                "Could not acquire lock to generate new ID, please try again later."
        );
        return ResponseEntity.status(HttpStatus.LOCKED).body(response);
    }

    @ExceptionHandler(NumeratorException.class)
    public ResponseEntity<ApiErrorResponse> handleNumeratorException(NumeratorException ex) {
        logger.error("Numerator error: ", ex);
        ApiErrorResponse response = new ApiErrorResponse(
                500,
                "Numerator Service Error",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(TransactionAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleTransactionAlreadyExists(TransactionAlreadyExistsException ex) {
        logger.warn("Transaction already exists: {}", ex.getMessage());
        ApiErrorResponse response = new ApiErrorResponse(
                409,
                "Transaction Conflict",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ReceivableAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleReceivableAlreadyExists(ReceivableAlreadyExistsException ex) {
        logger.warn("Receivable already exists: {}", ex.getMessage());
        ApiErrorResponse response = new ApiErrorResponse(
                409,
                "Receivable Conflict",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception ex) {
        Throwable cause = ex;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }

        if (cause instanceof TimeoutException) {
            logger.error("Timeout error detected: ", ex);
            ApiErrorResponse response = new ApiErrorResponse(
                    504,
                    "Timeout",
                    "The request timed out. Please try again later."
            );
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(response);
        }

        logger.error("Unhandled error in the API: ", ex);
        ApiErrorResponse response = new ApiErrorResponse(
                500,
                "Internal Server Error",
                "An unexpected error occurred"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}

