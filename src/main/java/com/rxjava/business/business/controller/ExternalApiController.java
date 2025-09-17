package com.rxjava.business.business.controller;

import com.rxjava.business.business.exception.BusinessExceptionFactory;
import com.rxjava.business.business.exception.HttpExceptionHandler;
import com.rxjava.business.business.mapper.CuentaMapper;
import com.rxjava.business.business.model.PostDto;
import com.rxjava.business.business.model.proxy.Cuenta;
import com.rxjava.business.business.model.response.AccountResponse;
import com.rxjava.business.business.service.ExternalApiService;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/external-api")
@Slf4j
public class ExternalApiController {
    private final ExternalApiService service;
    private final BusinessExceptionFactory exceptionFactory;
    private final HttpExceptionHandler httpExceptionHandler;
    private final CuentaMapper mapper;

    @GetMapping("/posts/{id}")
    public Single<PostDto> consumeApi1(@PathVariable("id") Integer id) {
        return service.getFromApi1(id);
    }

    @GetMapping("/posts/v2/{id}")
    public Maybe<ResponseEntity<Cuenta>> consumeApi2(@PathVariable("id") Integer id) {
        return service.getAccountById(id)
                //.onErrorResumeNext(exceptionFactory::handleErrorCuenta)
                //.onErrorResumeNext((Function<? super Throwable, ? extends MaybeSource<? extends Cuenta>>) httpExceptionHandler::handleHttpException)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build())
                .doOnError(error ->
                        log.error("Error fetching account with id {}: {}", id, error.getMessage()))
                .doOnSuccess(response ->
                        log.info("Successfully fetched account with id {}: {}", id, response.getBody()));
    }

    @GetMapping("/posts/v2")
    public Maybe<ResponseEntity<AccountResponse>> getAllAccounts() {
        return service.getAllAccounts()
                .map(ResponseEntity::ok)
                .toMaybe()
                .doOnSuccess(success -> log.info("Successfully fetched all accounts"))
                .doOnError(error -> log.error("Error fetching all accounts: {}", error.getMessage()));


    }

}
