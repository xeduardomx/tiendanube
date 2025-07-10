package com.tiendanube.eduardo.evaluacion.application.service;

import com.tiendanube.eduardo.evaluacion.infrastructure.external.receivable.dto.ReceivableDto;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.transaction.dto.TransactionDto;

public interface IReceivableCalculator {

    ReceivableDto calculateReceivable(TransactionDto transaction);
}
