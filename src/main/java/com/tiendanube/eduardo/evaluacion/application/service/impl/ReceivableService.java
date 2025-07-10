package com.tiendanube.eduardo.evaluacion.application.service.impl;

import com.tiendanube.eduardo.evaluacion.application.service.IReceivableService;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.receivable.dto.ReceivableDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReceivableService implements IReceivableService {

    @Override
    public List<ReceivableDto> listAll() {
        return null;
    }

    @Override
    public Optional<ReceivableDto> findById(String id) {
        return null;
    }

    @Override
    public ReceivableDto create(ReceivableDto receivableDto) {
        // Pending implementation
        return null;
    }

    @Override
    public Optional<ReceivableDto> update(String id, ReceivableDto updatedReceivable) {
        // Pending implementation
        return Optional.empty();
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
