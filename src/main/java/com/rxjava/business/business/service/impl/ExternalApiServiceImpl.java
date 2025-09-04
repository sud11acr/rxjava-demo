package com.rxjava.business.business.service.impl;

import com.rxjava.business.business.config.ExternalApiProperties;
import com.rxjava.business.business.model.PostDto;
import com.rxjava.business.business.proxy.HttpClientService;
import com.rxjava.business.business.service.ExternalApiService;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalApiServiceImpl implements ExternalApiService {
    private final ExternalApiProperties props;
    private final HttpClientService httpClientService;
    @Override
    public Single<PostDto> getFromApi1() {
        return httpClientService.get(props.url(), PostDto.class);
    }
}
