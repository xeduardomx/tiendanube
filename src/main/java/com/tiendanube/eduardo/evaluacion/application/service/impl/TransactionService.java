package com.tiendanube.eduardo.evaluacion.application.service.impl;

import com.tiendanube.eduardo.evaluacion.domain.exception.ReceivableAlreadyExistsException;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.receivable.client.IReceivableClient;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.receivable.dto.ReceivableDto;
import com.tiendanube.eduardo.evaluacion.application.dto.ReceivableCalculationResult;
import com.tiendanube.eduardo.evaluacion.application.mapper.ReceivableMapper;
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


@Service
public class TransactionService implements ITransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final INumeratorClient numeratorClient;
    private final ITransactionClient transactionClient;
    private final IReceivableClient receivableClient;


    public TransactionService(
            INumeratorClient numeratorClient,
            ITransactionClient transactionClient,
            IReceivableClient receivableClient) {
        this.numeratorClient = numeratorClient;
        this.transactionClient = transactionClient;
        this.receivableClient = receivableClient;
    }

    public MerchantsTransactionRequest create(MerchantsTransactionRequest merchantsTransactionRequest) {
        int newUniqueIdNum = acquireNextUniqueId();

        try {
            createTransactionIfNotExists(merchantsTransactionRequest, newUniqueIdNum);

            ReceivableCalculationResult result = calculateReceivable(merchantsTransactionRequest);

            createReceivableIfNotExists(result, newUniqueIdNum);

        } finally {
            numeratorClient.unlockNumerator();
        }

        return merchantsTransactionRequest;
    }


    public ReceivableCalculationResult calculateReceivable(MerchantsTransactionRequest merchantsTransactionRequest) {
        return ReceivableCalculator.calculateReceivable(
                merchantsTransactionRequest.getValue(),
                merchantsTransactionRequest.getMethod()
        );
    }

    private void createTransactionIfNotExists(MerchantsTransactionRequest request, int newUniqueIdNum) {
        TransactionDto existingTransaction = transactionClient.getTransactionById(newUniqueIdNum);

        if (existingTransaction == null) {
            logger.info("Transaction with id {} does not exist. Creating new transaction.", newUniqueIdNum);
            transactionClient.createTransaction(TransactionMapper.toTransactionDto(newUniqueIdNum, request));
        } else {
            throw new TransactionAlreadyExistsException("Transaction with id " + newUniqueIdNum + " already exists.");
        }
    }

    private void createReceivableIfNotExists(ReceivableCalculationResult result, int newUniqueIdNum) {
        ReceivableDto existingReceivable = receivableClient.getReceivableById(newUniqueIdNum);

        if (existingReceivable == null) {
            logger.info("Receivable with id {} does not exist. Creating new Receivable.", newUniqueIdNum);
            receivableClient.createReceivable(ReceivableMapper.toReceivable(newUniqueIdNum, result));
        } else {
            throw new ReceivableAlreadyExistsException("Receivable with id " + newUniqueIdNum + " already exists.");
        }
    }

    private int acquireNextUniqueId() {
        int oldIdNum = numeratorClient.getNumerator();
        int newUniqueIdNum = oldIdNum + 1;

        numeratorClient.lockNumerator();
        numeratorClient.testAndSetNumerator(oldIdNum, newUniqueIdNum);

        return newUniqueIdNum;
    }
}
