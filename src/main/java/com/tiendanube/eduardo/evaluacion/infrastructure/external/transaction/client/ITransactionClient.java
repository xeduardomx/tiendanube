package com.tiendanube.eduardo.evaluacion.infrastructure.external.transaction.client;

import com.tiendanube.eduardo.evaluacion.infrastructure.external.transaction.dto.TransactionDto;

public interface ITransactionClient {

    TransactionDto getTransactionById(int id);

    void createTransaction(TransactionDto request);
}

