package com.tiendanube.eduardo.evaluacion.application.service;


import com.tiendanube.eduardo.evaluacion.infrastructure.external.receivable.dto.ReceivableDto;

import java.util.List;
import java.util.Optional;

public interface IReceivableService {

    List<ReceivableDto> listAll();

    Optional<ReceivableDto> findById(String id);

    ReceivableDto create(ReceivableDto receivableDto);

    Optional<ReceivableDto> update(String id, ReceivableDto receivableDto);

    boolean delete(String id);
}
