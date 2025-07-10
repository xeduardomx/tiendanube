package com.tiendanube.eduardo.evaluacion.presentation.controller;

import com.tiendanube.eduardo.evaluacion.application.service.impl.TransactionService;
import com.tiendanube.eduardo.evaluacion.application.dto.TransactionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionRequest> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        TransactionRequest created = transactionService.create(transactionRequest);
        return ResponseEntity.ok(created);
    }
}
