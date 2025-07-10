package com.tiendanube.eduardo.evaluacion.infrastructure.external.transaction.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TransactionClient implements ITransactionClient {

    private static final Logger logger = LoggerFactory.getLogger(TransactionClient.class);

//    private final WebClient webClient;
//
//    public TransactionClient(WebClient webClient) {
//        this.webClient = webClient;
//    }
}

