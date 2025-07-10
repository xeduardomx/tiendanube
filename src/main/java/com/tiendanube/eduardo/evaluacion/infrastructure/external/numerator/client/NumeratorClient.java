package com.tiendanube.eduardo.evaluacion.infrastructure.external.numerator.client;

import com.tiendanube.eduardo.evaluacion.domain.exception.LockNotAcquiredException;
import com.tiendanube.eduardo.evaluacion.domain.exception.NumeratorException;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.numerator.dto.LockRequest;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.numerator.dto.TestAndSetRequest;
import com.tiendanube.eduardo.evaluacion.infrastructure.external.numerator.dto.TestAndSetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class NumeratorClient implements INumeratorClient {

    private static final Logger logger = LoggerFactory.getLogger(INumeratorClient.class);

    private final WebClient webClient;
    private final int lockTimeout;
    private final int lockNumeratorMaxRetries;
    private final int lockNumeratorRetryDelaySeconds;
    private final int unlockNumeratorMaxRetries;
    private final int unlockNumeratorRetryDelayMilliseconds;

    private static final int MIN_VALID_NUMERATOR = 0;

    public NumeratorClient(
            WebClient.Builder webClientBuilder,
            @Value("${numerator.api.base-url}") String baseUrl,
            @Value("${numerator.api.lock.timeout}") int lockTimeout,
            @Value("${numerator.retry.lock.max-retries}") int lockNumeratorMaxRetries,
            @Value("${numerator.retry.lock.delay-ms}") int lockNumeratorRetryDelaySeconds,
            @Value("${numerator.retry.unlock.max-retries}") int unlockNumeratorMaxRetries,
            @Value("${numerator.retry.unlock.delay-ms}") int unlockNumeratorRetryDelayMilliseconds
    ) {
        this.lockTimeout = lockTimeout;
        this.lockNumeratorMaxRetries = lockNumeratorMaxRetries;
        this.lockNumeratorRetryDelaySeconds = lockNumeratorRetryDelaySeconds;
        this.unlockNumeratorMaxRetries = unlockNumeratorMaxRetries;
        this.unlockNumeratorRetryDelayMilliseconds = unlockNumeratorRetryDelayMilliseconds;
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
                .filter(n -> n >= MIN_VALID_NUMERATOR)
                .blockOptional()
                .orElseThrow(() -> new NumeratorException("No valid numerator was obtained"));
    }

    @Override
    public int lockNumerator() {
        return webClient.post()
                .uri("/numerator/lock")
                .bodyValue(new LockRequest(lockTimeout))
                .retrieve()
                .onStatus(
                        status -> !status.is2xxSuccessful(),
                        response -> Mono.error(new RuntimeException("Error locking numerator: " + response.statusCode()))
                )
                .bodyToMono(NumeratorResponse.class)
                .map(NumeratorResponse::numerator)
                .retryWhen(Retry.fixedDelay(lockNumeratorMaxRetries, Duration.ofSeconds(lockNumeratorRetryDelaySeconds)))
                .timeout(Duration.ofSeconds(10))
                .blockOptional()
                .orElseThrow(() -> new LockNotAcquiredException("No valid numerator received from lock endpoint after retries"));
    }


    @Override
    public boolean testAndSetNumerator(int oldValue, int newValue) {
        return webClient.put()
                .uri("/numerator/test-and-set")
                .bodyValue(new TestAndSetRequest(oldValue, newValue))
                .retrieve()
                .onStatus(status -> status.value() == 400,
                        response -> Mono.error(new NumeratorException("Bad request in test-and-set")))
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> Mono.error(new RuntimeException("Unexpected error: " + response.statusCode())))
                .bodyToMono(TestAndSetResponse.class)
                .map(TestAndSetResponse::isSuccess)
                .blockOptional()
                .orElseThrow(() -> new NumeratorException("Test and set operation failed with empty response"));
    }

    @Override
    public void unlockNumerator() {
        webClient.delete()
                .uri("/numerator/lock")
                .retrieve()
                .onStatus(
                        status -> !status.is2xxSuccessful(),
                        response -> Mono.error(new NumeratorException("Error unlocking numerator: " + response.statusCode()))
                )
                .bodyToMono(MessageResponse.class)
                .retryWhen(
                        reactor.util.retry.Retry.fixedDelay(unlockNumeratorMaxRetries, Duration.ofMillis(unlockNumeratorRetryDelayMilliseconds))
                ).block();
    }

    private static record MessageResponse(String message) {}

    private static record NumeratorResponse(int numerator) {}
}

