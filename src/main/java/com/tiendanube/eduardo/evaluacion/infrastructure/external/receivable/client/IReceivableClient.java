package com.tiendanube.eduardo.evaluacion.infrastructure.external.receivable.client;

import com.tiendanube.eduardo.evaluacion.infrastructure.external.receivable.dto.ReceivableDto;

public interface IReceivableClient {

    ReceivableDto getReceivableById(int id);

    void createReceivable(ReceivableDto request);
}

