package com.tiendanube.eduardo.evaluacion.infrastructure.external.numerator.dto;

public class TestAndSetRequest {
    private int oldValue;
    private int newValue;

    public TestAndSetRequest(int oldValue, int newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public int getOldValue() {
        return oldValue;
    }

    public void setOldValue(int oldValue) {
        this.oldValue = oldValue;
    }

    public int getNewValue() {
        return newValue;
    }

    public void setNewValue(int newValue) {
        this.newValue = newValue;
    }
}

