package com.rxjava.business.business.service;

import com.rxjava.business.business.model.PostDto;
import io.reactivex.Single;

public interface ExternalApiService {
    public Single<PostDto> getFromApi1(Integer id);
}
