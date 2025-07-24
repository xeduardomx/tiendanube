package com.tiendanube.eduardo.evaluacion.application.service.impl;

import com.tiendanube.eduardo.evaluacion.application.dto.ReceivableSummary;
import com.tiendanube.eduardo.evaluacion.application.mapper.ReceivableTransactionMapper;
import com.tiendanube.eduardo.evaluacion.domain.exception.NumeratorException;
import com.tiendanube.eduardo.evaluacion.domain.exception.ReceivableAlreadyExistsException;
import com.tiendanube.eduardo.evaluacion.domain.model.ReceivableTransaction;
import com.tiendanube.eduardo.evaluacion.domain.repository.ReceivableTransactionRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Service
public class TransactionService implements ITransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final INumeratorClient numeratorClient;
    private final ITransactionClient transactionClient;
    private final IReceivableClient receivableClient;
    private final ReceivableTransactionRepository repository;


    public TransactionService(
            INumeratorClient numeratorClient,
            ITransactionClient transactionClient,
            IReceivableClient receivableClient, ReceivableTransactionRepository repository) {
        this.numeratorClient = numeratorClient;
        this.transactionClient = transactionClient;
        this.receivableClient = receivableClient;
        this.repository = repository;
    }

    @Transactional(rollbackFor = Exception.class)
    public ReceivableTransaction create(MerchantsTransactionRequest merchantsTransactionRequest) {


        int newUniqueIdNum = acquireNextUniqueId();

        ReceivableCalculationResult result = calculateReceivable(merchantsTransactionRequest);

        ReceivableTransaction receivableTransaction = ReceivableTransactionMapper.toEntity(
                newUniqueIdNum,
                merchantsTransactionRequest,
                result
        );

        repository.save(receivableTransaction);

        createTransactionIfNotExists(merchantsTransactionRequest, newUniqueIdNum);

        createReceivableIfNotExists(result, newUniqueIdNum);

        return receivableTransaction;
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

        try {
            numeratorClient.testAndSetNumerator(oldIdNum, newUniqueIdNum);
        } catch (NumeratorException e) {
            testAndSetWithLock(oldIdNum, newUniqueIdNum);
        }

        return newUniqueIdNum;
    }

    private void testAndSetWithLock(int oldIdNum, int newUniqueIdNum) {
        numeratorClient.lockNumerator();
        numeratorClient.testAndSetNumerator(oldIdNum, newUniqueIdNum);
        numeratorClient.unlockNumerator();
    }

    public List<ReceivableTransaction> getTransactionsByMerchant(String merchantId) {
        return repository.findByMerchantId(merchantId);
    }

    public ReceivableSummary calculateReceivableSummary(String merchantId, LocalDate startDate, LocalDate endDate) {
        List<ReceivableTransaction> transactions = repository.findByMerchantIdAndCreateDateBetween(merchantId, startDate, endDate);

        BigDecimal totalReceivable = BigDecimal.ZERO;
        BigDecimal futureReceivable = BigDecimal.ZERO;
        BigDecimal totalCommissions = BigDecimal.ZERO;
        BigDecimal totalReceived = BigDecimal.ZERO;

        LocalDate today = LocalDate.now();

        for (ReceivableTransaction tx : transactions) {
            totalReceivable = totalReceivable.add(tx.getTotal());

            //TODO los estados deben ser enum
            if ("waiting_funds".equalsIgnoreCase(tx.getStatus()) && tx.getPaymentDay() != null && tx.getPaymentDay().isAfter(today)) {
                futureReceivable = futureReceivable.add(tx.getTotal());
            }

            if ("paid".equalsIgnoreCase(tx.getStatus())) {
                totalReceived = totalReceived.add(tx.getTotal());
            }

            totalCommissions = totalCommissions.add(tx.getDiscount());
        }

        return new ReceivableSummary(totalReceivable, futureReceivable, totalCommissions, totalReceived);
    }

}
