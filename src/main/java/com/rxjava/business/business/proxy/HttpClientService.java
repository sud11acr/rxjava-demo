package com.rxjava.business.business.proxy;

import io.reactivex.Single;

public interface HttpClientService {
    public <T> Single<T> get(String url, Class<T> responseType);
}
