package com.tiendanube.eduardo.evaluacion.presentation.controller;

import com.tiendanube.eduardo.evaluacion.application.service.impl.TransactionService;
import com.tiendanube.eduardo.evaluacion.application.dto.MerchantsTransactionRequest;
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
    public ResponseEntity<MerchantsTransactionRequest> createTransaction(@RequestBody MerchantsTransactionRequest merchantsTransactionRequest) {
        MerchantsTransactionRequest created = transactionService.create(merchantsTransactionRequest);
        return ResponseEntity.ok(created);
    }
}
