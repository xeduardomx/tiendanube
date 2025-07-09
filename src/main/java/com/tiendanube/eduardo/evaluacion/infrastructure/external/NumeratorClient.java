package com.tiendanube.eduardo.evaluacion.infrastructure.external;

import com.tiendanube.eduardo.evaluacion.application.NumeratorService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class NumeratorClient implements NumeratorService {

    private static final Logger logger = LoggerFactory.getLogger(NumeratorService.class);

    private final WebClient webClient;

    public NumeratorClient(
            WebClient.Builder webClientBuilder,
            @Value("${numerator.api.base-url}") String baseUrl
    ) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @Override
    public int getNumerator() {
        return webClient.get()
                .uri("/numerator")
                .retrieve()
                .onStatus(
                        status -> !status.is2xxSuccessful(),
                        response -> Mono.error(new RuntimeException("Error response from numerator API: " + response.statusCode()))
                )
                .bodyToMono(NumeratorResponse.class)
                .map(NumeratorResponse::numerator)
                .filter(n -> n >= 0)
                .blockOptional()
                .orElseGet(() -> {
                    logger.warn("No valid numerator was obtained");
                    return -1;
                });

    }

    private static record NumeratorResponse(int numerator) {}
}

