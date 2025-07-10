package com.tiendanube.eduardo.evaluacion.application.service.impl;

import com.tiendanube.eduardo.evaluacion.application.dto.ReceivableRequest;
import com.tiendanube.eduardo.evaluacion.application.service.IReceivableService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReceivableService implements IReceivableService {

    @Override
    public List<ReceivableRequest> listAll() {
        return null;
    }

    @Override
    public Optional<ReceivableRequest> findById(String id) {
        return null;
    }

    @Override
    public ReceivableRequest create(ReceivableRequest receivableRequest) {
        // Pending implementation
        return null;
    }

    @Override
    public Optional<ReceivableRequest> update(String id, ReceivableRequest updatedReceivable) {
        // Pending implementation
        return Optional.empty();
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
