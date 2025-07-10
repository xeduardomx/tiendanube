package com.tiendanube.eduardo.evaluacion.infrastructure.external.transaction.client;

import com.tiendanube.eduardo.evaluacion.domain.exception.TransactionException;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.transaction.dto.TransactionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class TransactionClient implements ITransactionClient {

    private static final Logger logger = LoggerFactory.getLogger(TransactionClient.class);

    private final WebClient webClient;

    public TransactionClient(
            WebClient.Builder webClientBuilder,
            @Value("${transaction.api.base-url}") String baseUrl
    ) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @Override
    public TransactionDto getTransactionById(int id) {
        return webClient.get()
                .uri("/transactions/{id}", id)
                .retrieve()
                .onStatus(
                        status -> status.isError() && status.value() != 404,
                        response -> {
                            logger.error("Error retrieving transaction with id {}: {}", id, response.statusCode());
                            return Mono.error(new TransactionException("Failed to retrieve transaction: " + response.statusCode()));
                        }
                )
                .bodyToMono(TransactionDto.class)
                .onErrorResume(e -> {
                    logger.error("Exception while retrieving transaction with id {}: {}", id, e.getMessage());
                    return Mono.empty();
                })
                .block();
    }


    @Override
    public void createTransaction(TransactionDto request) {
        webClient.post()
                .uri("/transactions")
                .bodyValue(request)
                .retrieve()
                .onStatus(
                        status -> !status.is2xxSuccessful(),
                        response -> {
                            logger.error("Error response from transaction API: {}", response.statusCode());
                            return Mono.error(new TransactionException("Error creating transaction: " + response.statusCode()));
                        }
                )
                .toBodilessEntity()
                .blockOptional()
                .orElseThrow(() -> new TransactionException("Empty response received when creating transaction"));
    }

}
