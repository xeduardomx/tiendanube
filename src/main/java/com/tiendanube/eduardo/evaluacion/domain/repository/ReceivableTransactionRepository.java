package com.tiendanube.eduardo.evaluacion.domain.repository;

import com.tiendanube.eduardo.evaluacion.domain.model.ReceivableTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceivableTransactionRepository extends JpaRepository<ReceivableTransaction, String> {
    List<ReceivableTransaction> findByMerchantIdAndCreateDateBetween(String merchantId, LocalDate startDate, LocalDate endDate);
    List<ReceivableTransaction> findByMerchantId(String merchantId);
}

