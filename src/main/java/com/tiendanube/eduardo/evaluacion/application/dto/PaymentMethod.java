package com.tiendanube.eduardo.evaluacion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PaymentMethod {
    @JsonProperty("debit_card")
    DEBIT_CARD,

    @JsonProperty("credit_card")
    CREDIT_CARD
}

