package com.rxjava.business.business.service;

import com.rxjava.business.business.model.PostDto;
import com.rxjava.business.business.model.proxy.Cuenta;
import com.rxjava.business.business.model.response.AccountResponse;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

public interface ExternalApiService {
    public Single<PostDto> getFromApi1(Integer id);
    Maybe<Cuenta> getAccountById(Integer id);
    Single<AccountResponse> getAllAccounts();
}
