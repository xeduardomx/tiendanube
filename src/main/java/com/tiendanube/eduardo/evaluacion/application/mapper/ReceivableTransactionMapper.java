package com.tiendanube.eduardo.evaluacion.application.mapper;

import com.tiendanube.eduardo.evaluacion.application.dto.MerchantsTransactionRequest;
import com.tiendanube.eduardo.evaluacion.application.dto.ReceivableCalculationResult;
import com.tiendanube.eduardo.evaluacion.domain.model.ReceivableTransaction;

import java.time.LocalDate;

import static com.tiendanube.eduardo.evaluacion.application.dto.PaymentMethod.CREDIT_CARD;
import static com.tiendanube.eduardo.evaluacion.application.dto.PaymentMethod.DEBIT_CARD;

public class ReceivableTransactionMapper {
    public static ReceivableTransaction toEntity(int id, MerchantsTransactionRequest request, ReceivableCalculationResult result) {
        ReceivableTransaction entity = new ReceivableTransaction();
        entity.setId(String.valueOf(id));
        entity.setStatus(result.getStatus());
        entity.setCreateDate(LocalDate.now());
        entity.setValue(request.getValue());
        entity.setMethod(request.getMethod().toString());
        entity.setDescription(request.getDescription());

        entity.setCardNumber(request.getCardNumber());
        entity.setCardHolderName(request.getCardHolderName());
        entity.setCardExpirationDate(request.getCardExpirationDate());
        entity.setCardCvv(request.getCardCvv());
        entity.setMerchantId(request.getMerchantId());

        entity.setSubtotal(result.getSubtotal());
        entity.setDiscount(result.getDiscount());
        entity.setTotal(result.getTotal());

        if (request.getMethod() == DEBIT_CARD) {
            entity.setPaymentDay(LocalDate.now());
        } else if (request.getMethod() == CREDIT_CARD) {
            entity.setPaymentDay(LocalDate.now().plusDays(30));
        }

        return entity;
    }
}

