package com.tiendanube.eduardo.evaluacion.application.mapper;

import com.tiendanube.eduardo.evaluacion.application.dto.MerchantsTransactionRequest;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.transaction.dto.TransactionDto;

public class TransactionMapper {

    public static TransactionDto toTransactionDto(int id, MerchantsTransactionRequest request) {
        String last4Digits = request.getCardNumber();
        if (last4Digits != null && last4Digits.length() > 4) {
            last4Digits = last4Digits.substring(last4Digits.length() - 4);
        }

        return new TransactionDto(
                String.valueOf(id),
                request.getValue().toPlainString(),
                request.getDescription(),
                request.getMethod().toString(),
                last4Digits,
                request.getCardHolderName(),
                request.getCardExpirationDate(),
                request.getCardCvv()
        );
    }
}
