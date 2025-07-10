package com.tiendanube.eduardo.evaluacion.application.service;

import com.tiendanube.eduardo.evaluacion.application.dto.MerchantsTransactionRequest;
import com.tiendanube.eduardo.evaluacion.application.dto.ReceivableSummary;
import com.tiendanube.eduardo.evaluacion.domain.model.ReceivableTransaction;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionService {
    ReceivableTransaction create(MerchantsTransactionRequest merchantsTransactionRequest);

    List<ReceivableTransaction> getTransactionsByMerchant(String merchantId);

    ReceivableSummary calculateReceivableSummary(String merchantId, LocalDate startDate, LocalDate endDate);
}
