package com.tiendanube.eduardo.evaluacion.application.service;

import com.tiendanube.eduardo.evaluacion.application.dto.MerchantsTransactionRequest;
import com.tiendanube.eduardo.evaluacion.domain.model.ReceivableTransaction;

public interface ITransactionService {
    ReceivableTransaction create(MerchantsTransactionRequest merchantsTransactionRequest);
}
