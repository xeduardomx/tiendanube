package com.tiendanube.eduardo.evaluacion.application.service;

import com.tiendanube.eduardo.evaluacion.application.dto.MerchantsTransactionRequest;


import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    List<MerchantsTransactionRequest> listAll();
    Optional<MerchantsTransactionRequest> findById(String id);
    MerchantsTransactionRequest create(MerchantsTransactionRequest merchantsTransactionRequest);
    boolean delete(String id);
}
