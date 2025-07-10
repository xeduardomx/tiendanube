package com.tiendanube.eduardo.evaluacion.application.service.impl;

import com.tiendanube.eduardo.evaluacion.infrastructure.external.numerator.client.INumeratorClient;
import com.tiendanube.eduardo.evaluacion.application.dto.TransactionRequest;
import com.tiendanube.eduardo.evaluacion.application.service.ITransactionService;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.transaction.client.ITransactionClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService implements ITransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final INumeratorClient numeratorClient;
    private final ITransactionClient transactionClient;


    public TransactionService(
            INumeratorClient numeratorClient,
            ITransactionClient transactionClient
    ) {
        this.numeratorClient = numeratorClient;
        this.transactionClient = transactionClient;
    }

    public List<TransactionRequest> listAll() {
        return null;
    }

    public Optional<TransactionRequest> findById(String id) {
        return null;
    }

    public TransactionRequest create(TransactionRequest transactionRequest) {
        int newIdNum = acquireNextUniqueId();

        String last4Digits = transactionRequest.getCardNumber();
        if (last4Digits != null && last4Digits.length() > 4) {
            last4Digits = last4Digits.substring(last4Digits.length() - 4);
        }

        String newId = String.valueOf(newIdNum);

        // TODO
        //  registrar transaccion
        //  Registrar receivable
        //  Usando ID unico generado

        numeratorClient.unlockNumerator();

        return null;
    }

    private int acquireNextUniqueId() {
        int oldIdNum = numeratorClient.getNumerator();
        int newIdNum = oldIdNum + 1;

        numeratorClient.lockNumerator();
        numeratorClient.testAndSetNumerator(oldIdNum, newIdNum);

        return newIdNum;
    }

    public boolean delete(String id) {
        return false;
    }
}
