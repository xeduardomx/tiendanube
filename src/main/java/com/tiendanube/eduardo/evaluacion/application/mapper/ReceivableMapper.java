package com.tiendanube.eduardo.evaluacion.application.mapper;

import com.tiendanube.eduardo.evaluacion.infrastructure.external.receivable.dto.ReceivableDto;
import com.tiendanube.eduardo.evaluacion.application.dto.ReceivableCalculationResult;
import com.tiendanube.eduardo.evaluacion.util.DateTimeUtil;

public class ReceivableMapper {

    public static ReceivableDto toReceivable(int id, ReceivableCalculationResult result) {

        return new ReceivableDto.Builder()
                .id(String.valueOf(id))
                .status(result.getStatus())
                .createDate(DateTimeUtil.getCurrentDateTimeFormatted())
                .subtotal(result.getSubtotal())
                .discount(result.getDiscount())
                .total(result.getTotal())
                .build();
    }
}

