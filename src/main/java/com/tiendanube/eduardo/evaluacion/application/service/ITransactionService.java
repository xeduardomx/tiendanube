package com.tiendanube.eduardo.evaluacion.application.service;

import com.tiendanube.eduardo.evaluacion.application.dto.MerchantsTransactionRequest;

public interface ITransactionService {
    MerchantsTransactionRequest create(MerchantsTransactionRequest merchantsTransactionRequest);
}
