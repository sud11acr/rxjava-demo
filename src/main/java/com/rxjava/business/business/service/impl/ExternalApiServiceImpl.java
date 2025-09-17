package com.rxjava.business.business.service.impl;

import com.rxjava.business.business.config.ExternalApiProperties;
import com.rxjava.business.business.model.PostDto;
import com.rxjava.business.business.model.proxy.Cuenta;
import com.rxjava.business.business.model.response.AccountResponse;
import com.rxjava.business.business.proxy.AuthenticatedUserProxy;
import com.rxjava.business.business.proxy.HttpClientService;
import com.rxjava.business.business.service.ExternalApiService;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalApiServiceImpl implements ExternalApiService {
    private final ExternalApiProperties props;
    private final HttpClientService httpClientService;
    private final AuthenticatedUserProxy proxy;
    @Override
    public Single<PostDto> getFromApi1(Integer id) {
        return httpClientService.get(props.url(), id, PostDto.class);
    }

    @Override
    public Maybe<Cuenta> getAccountById(Integer id) {
        return proxy.findById(id);
    }

    @Override
    public Single<AccountResponse> getAllAccounts() {
        return Single.fromObservable(proxy.findAll())
                .subscribeOn(Schedulers.io())
                .map(accounts ->
                        AccountResponse.builder().accounts(accounts).build())
                .doOnSuccess(success -> log.info("Request to get all accounts was successful"))
                .doOnError(ex ->
                        log.error("Error occurred while fetching all accounts: {}", ex.getMessage()));

    }
}
