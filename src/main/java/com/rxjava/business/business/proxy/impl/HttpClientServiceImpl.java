package com.rxjava.business.business.proxy.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rxjava.business.business.proxy.HttpClientService;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
@Component
public class HttpClientServiceImpl implements HttpClientService {
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public <T> Single<T> get(String url, Integer id, Class<T> responseType) {
        String finalUrl = String.format("%s/%s", url, id);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(finalUrl))
                .GET()
                .build();

        return Single.fromFuture(
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenApply(resp -> {
                            try {
                                return objectMapper.readValue(resp.body(), responseType);
                            } catch (Exception e) {
                                throw new RuntimeException("Error parseando JSON", e);
                            }
                        })
        );
    }
}
