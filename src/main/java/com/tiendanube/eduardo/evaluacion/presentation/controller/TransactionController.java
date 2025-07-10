package com.tiendanube.eduardo.evaluacion.presentation.controller;

import com.tiendanube.eduardo.evaluacion.application.dto.ReceivableSummary;
import com.tiendanube.eduardo.evaluacion.application.service.ITransactionService;
import com.tiendanube.eduardo.evaluacion.application.dto.MerchantsTransactionRequest;
import com.tiendanube.eduardo.evaluacion.domain.model.ReceivableTransaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<ReceivableTransaction> createTransaction(@RequestBody MerchantsTransactionRequest merchantsTransactionRequest) {
        ReceivableTransaction created = transactionService.create(merchantsTransactionRequest);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/merchant/{merchantId}")
    public List<ReceivableTransaction> getTransactionsByMerchant(@PathVariable String merchantId) {
        return transactionService.getTransactionsByMerchant(merchantId);
    }

    @GetMapping("/summary")
    public ReceivableSummary getReceivableSummary(
            @RequestParam String merchantId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
        return transactionService.calculateReceivableSummary(merchantId, start, end);
    }
}
