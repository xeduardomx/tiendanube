package com.tiendanube.eduardo.evaluacion.presentation.controller;

import com.tiendanube.eduardo.evaluacion.application.NumeratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumeratorController {

    private final NumeratorService numeratorService;

    public NumeratorController(NumeratorService numeratorService) {
        this.numeratorService = numeratorService;
    }

    @GetMapping("/numerator")
    public int getNumerator() {
        return numeratorService.getNumerator();
    }
}

