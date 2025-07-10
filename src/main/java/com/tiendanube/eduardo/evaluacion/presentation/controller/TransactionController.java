package com.tiendanube.eduardo.evaluacion.presentation.controller;

import com.tiendanube.eduardo.evaluacion.application.service.impl.TransactionService;
import com.tiendanube.eduardo.evaluacion.application.dto.MerchantsTransactionRequest;
import com.tiendanube.eduardo.evaluacion.domain.model.ReceivableTransaction;
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
    public ResponseEntity<ReceivableTransaction> createTransaction(@RequestBody MerchantsTransactionRequest merchantsTransactionRequest) {
        ReceivableTransaction created = transactionService.create(merchantsTransactionRequest);
        return ResponseEntity.ok(created);
    }
}
