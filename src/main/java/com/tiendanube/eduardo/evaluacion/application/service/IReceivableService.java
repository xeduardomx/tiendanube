package com.tiendanube.eduardo.evaluacion.application.service;


import com.tiendanube.eduardo.evaluacion.application.dto.ReceivableRequest;

import java.util.List;
import java.util.Optional;

public interface IReceivableService {

    List<ReceivableRequest> listAll();

    Optional<ReceivableRequest> findById(String id);

    ReceivableRequest create(ReceivableRequest receivableRequest);

    Optional<ReceivableRequest> update(String id, ReceivableRequest receivableRequest);

    boolean delete(String id);
}
