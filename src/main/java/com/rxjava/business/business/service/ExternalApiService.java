package com.rxjava.business.business.service;

import com.rxjava.business.business.model.PostDto;
import com.rxjava.business.business.model.proxy.Cuenta;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface ExternalApiService {
    public Single<PostDto> getFromApi1(Integer id);
    Maybe<Cuenta> getAccountById(Integer id);
}
