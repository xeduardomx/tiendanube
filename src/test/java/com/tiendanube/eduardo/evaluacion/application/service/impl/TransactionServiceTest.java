package com.tiendanube.eduardo.evaluacion.application.service.impl;

import com.tiendanube.eduardo.evaluacion.application.dto.ReceivableSummary;
import com.tiendanube.eduardo.evaluacion.domain.model.ReceivableTransaction;
import com.tiendanube.eduardo.evaluacion.domain.repository.ReceivableTransactionRepository;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.numerator.client.INumeratorClient;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.receivable.client.IReceivableClient;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.transaction.client.ITransactionClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    private ReceivableTransactionRepository repository;

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        repository = mock(ReceivableTransactionRepository.class);
        INumeratorClient numeratorClient = mock(INumeratorClient.class);
        ITransactionClient transactionClient = mock(ITransactionClient.class);
        IReceivableClient receivableClient = mock(IReceivableClient.class);

        transactionService = new TransactionService(numeratorClient, transactionClient, receivableClient, repository);
    }

    @Nested
    @DisplayName("Tests for calculateReceivableSummary")
    class CalculateReceivableSummaryTests {

        @Test
        @DisplayName("Should calculate correct summary with mixed transaction statuses")
        void testCalculateReceivableSummary_MixedStatuses() {
            String merchantId = "merchant123";
            LocalDate startDate = LocalDate.of(2025, 7, 1);
            LocalDate endDate = LocalDate.of(2025, 7, 10);

            LocalDate today = LocalDate.now();

            ReceivableTransaction tx1 = new ReceivableTransaction();
            tx1.setTotal(new BigDecimal("100.00"));
            tx1.setStatus("waiting_funds");
            tx1.setPaymentDay(today.plusDays(5));
            tx1.setDiscount(new BigDecimal("5.00"));

            ReceivableTransaction tx2 = new ReceivableTransaction();
            tx2.setTotal(new BigDecimal("200.00"));
            tx2.setStatus("paid");
            tx2.setPaymentDay(today.minusDays(3));
            tx2.setDiscount(new BigDecimal("10.00"));

            ReceivableTransaction tx3 = new ReceivableTransaction();
            tx3.setTotal(new BigDecimal("50.00"));
            tx3.setStatus("waiting_funds");
            tx3.setPaymentDay(today.minusDays(1));
            tx3.setDiscount(new BigDecimal("2.50"));

            List<ReceivableTransaction> transactions = Arrays.asList(tx1, tx2, tx3);

            when(repository.findByMerchantIdAndCreateDateBetween(merchantId, startDate, endDate))
                    .thenReturn(transactions);

            ReceivableSummary summary = transactionService.calculateReceivableSummary(merchantId, startDate, endDate);

            assertAll("Validate summary values",
                    () -> assertEquals(new BigDecimal("350.00"), summary.getTotalReceivable(), "Total receivable"),
                    () -> assertEquals(new BigDecimal("100.00"), summary.getFutureReceivable(), "Future receivable"),
                    () -> assertEquals(new BigDecimal("200.00"), summary.getTotalReceived(), "Total received"),
                    () -> assertEquals(new BigDecimal("17.50"), summary.getTotalCommissions(), "Total commissions")
            );
        }

        @Test
        @DisplayName("Should return zeros when no transactions found")
        void testCalculateReceivableSummary_NoTransactions() {
            String merchantId = "merchantEmpty";
            LocalDate startDate = LocalDate.now().minusDays(30);
            LocalDate endDate = LocalDate.now();

            when(repository.findByMerchantIdAndCreateDateBetween(merchantId, startDate, endDate))
                    .thenReturn(Collections.emptyList());

            ReceivableSummary summary = transactionService.calculateReceivableSummary(merchantId, startDate, endDate);

            assertAll("Validate zero summary values",
                    () -> assertEquals(BigDecimal.ZERO, summary.getTotalReceivable(), "Total receivable"),
                    () -> assertEquals(BigDecimal.ZERO, summary.getFutureReceivable(), "Future receivable"),
                    () -> assertEquals(BigDecimal.ZERO, summary.getTotalReceived(), "Total received"),
                    () -> assertEquals(BigDecimal.ZERO, summary.getTotalCommissions(), "Total commissions")
            );
        }
    }

    @Nested
    @DisplayName("Tests for getTransactionsByMerchant")
    class GetTransactionsByMerchantTests {

        @Test
        @DisplayName("Should return transactions for given merchant")
        void testGetTransactionsByMerchant_Found() {
            String merchantId = "merchant456";

            ReceivableTransaction tx = new ReceivableTransaction();
            tx.setMerchantId(merchantId);
            tx.setTotal(new BigDecimal("123.45"));

            List<ReceivableTransaction> expectedList = Collections.singletonList(tx);

            when(repository.findByMerchantId(merchantId)).thenReturn(expectedList);

            List<ReceivableTransaction> result = transactionService.getTransactionsByMerchant(merchantId);

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(merchantId, result.get(0).getMerchantId());
        }

        @Test
        @DisplayName("Should return empty list when no transactions found")
        void testGetTransactionsByMerchant_Empty() {
            String merchantId = "nonexistent";

            when(repository.findByMerchantId(merchantId)).thenReturn(Collections.emptyList());

            List<ReceivableTransaction> result = transactionService.getTransactionsByMerchant(merchantId);

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

}
