package com.tiendanube.eduardo.evaluacion.application.service.impl;

import com.tiendanube.eduardo.evaluacion.application.mapper.TransactionMapper;
import com.tiendanube.eduardo.evaluacion.domain.exception.TransactionAlreadyExistsException;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.numerator.client.INumeratorClient;
import com.tiendanube.eduardo.evaluacion.application.dto.MerchantsTransactionRequest;
import com.tiendanube.eduardo.evaluacion.application.service.ITransactionService;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.transaction.client.ITransactionClient;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.transaction.dto.TransactionDto;
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

    public List<MerchantsTransactionRequest> listAll() {
        return null;
    }

    public Optional<MerchantsTransactionRequest> findById(String id) {
        return null;
    }

    public MerchantsTransactionRequest create(MerchantsTransactionRequest merchantsTransactionRequest) {
        int newIdNum = acquireNextUniqueId();

        createTransactionIfNotExists(merchantsTransactionRequest, newIdNum);

        // TODO
        //  Registrar receivable


        numeratorClient.unlockNumerator();

        return null;
    }

    private void createTransactionIfNotExists(MerchantsTransactionRequest request, int newIdNum) {
        TransactionDto existingTransaction = transactionClient.getTransactionById(newIdNum);

        if (existingTransaction == null) {
            logger.info("Transaction with id {} does not exist. Creating new transaction.", newIdNum);
            transactionClient.createTransaction(TransactionMapper.toTransactionDto(newIdNum, request));
        } else {
            throw new TransactionAlreadyExistsException("Transaction with id " + newIdNum + " already exists.");
        }
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
