package com.rxjava.business.business.service.impl;

import com.rxjava.business.business.config.ExternalApiProperties;
import com.rxjava.business.business.model.PostDto;
import com.rxjava.business.business.model.proxy.Cuenta;
import com.rxjava.business.business.proxy.AuthenticatedUserProxy;
import com.rxjava.business.business.proxy.HttpClientService;
import com.rxjava.business.business.service.ExternalApiService;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
