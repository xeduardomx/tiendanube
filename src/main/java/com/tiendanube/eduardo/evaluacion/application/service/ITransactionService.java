package com.tiendanube.eduardo.evaluacion.application.service;

import com.tiendanube.eduardo.evaluacion.application.dto.TransactionRequest;


import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    List<TransactionRequest> listAll();
    Optional<TransactionRequest> findById(String id);
    TransactionRequest create(TransactionRequest transactionRequest);
    boolean delete(String id);
}
