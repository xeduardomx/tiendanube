package com.tiendanube.eduardo.evaluacion.infrastructure.external.receivable.client;

import com.tiendanube.eduardo.evaluacion.domain.exception.ReceivableException;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.receivable.dto.ReceivableDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ReceivableClient implements IReceivableClient{

    private static final Logger logger = LoggerFactory.getLogger(ReceivableClient.class);

    private final WebClient webClient;

    public ReceivableClient(
            WebClient.Builder webClientBuilder,
            @Value("${receivable.api.base-url}") String baseUrl
    ) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @Override
    public ReceivableDto getReceivableById(int id) {
        return webClient.get()
                .uri("/receivables/{id}", id)
                .retrieve()
                .onStatus(
                        status -> status.isError() && status.value() != 404,
                        response -> {
                            logger.error("Error retrieving receivable with id {}: {}", id, response.statusCode());
                            return Mono.error(new ReceivableException("Failed to retrieve receivable: " + response.statusCode()));
                        }
                )
                .bodyToMono(ReceivableDto.class)
                .onErrorResume(e -> {
                    logger.error("Exception while retrieving receivable with id {}: {}", id, e.getMessage());
                    return Mono.empty();
                })
                .block();
    }

    public void createReceivable(ReceivableDto receivableDto) {
        webClient.post()
                .uri("/receivables")
                .bodyValue(receivableDto)
                .retrieve()
                .onStatus(
                        status -> !status.is2xxSuccessful(),
                        response -> {
                            logger.error("Error response from receivable API: {}", response.statusCode());
                            return Mono.error(new ReceivableException("Error creating receivable: " + response.statusCode()));
                        }
                )
                .toBodilessEntity()
                .blockOptional()
                .orElseThrow(() -> new ReceivableException("Empty response received when creating receivable"));
    }
}

