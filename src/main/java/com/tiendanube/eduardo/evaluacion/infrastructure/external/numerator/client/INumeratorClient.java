package com.tiendanube.eduardo.evaluacion.infrastructure.external.numerator.client;

public interface INumeratorClient {
    int getNumerator();

    int lockNumerator();

    boolean testAndSetNumerator(int oldValue, int newValue);

    void unlockNumerator();
}

