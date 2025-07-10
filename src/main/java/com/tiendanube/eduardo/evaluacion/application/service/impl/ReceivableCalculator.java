package com.tiendanube.eduardo.evaluacion.application.service.impl;

import com.tiendanube.eduardo.evaluacion.application.dto.PaymentMethod;
import com.tiendanube.eduardo.evaluacion.application.dto.ReceivableCalculationResult;

import java.math.BigDecimal;

public class ReceivableCalculator{

    public static ReceivableCalculationResult calculateReceivable(
            BigDecimal subtotal,
            PaymentMethod method
    ) {
        BigDecimal commissionRate;
        String status;

        //TODO Los valores se deben definir en properties, el estado deber ser un enum
        switch (method) {
            case DEBIT_CARD -> {
                commissionRate = new BigDecimal("0.02");
                status = "paid";
            }
            case CREDIT_CARD -> {
                commissionRate = new BigDecimal("0.04");
                status = "waiting_funds";
            }
            default -> throw new IllegalArgumentException("Unknown payment method: " + method);
        }

        BigDecimal discount = subtotal.multiply(commissionRate);
        BigDecimal total = subtotal.subtract(discount);

        return ReceivableCalculationResult.builder()
                .subtotal(subtotal)
                .discount(discount)
                .total(total)
                .status(status)
                .build();
    }
}
