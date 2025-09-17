package com.rxjava.business.business.dao.impl;

import com.rxjava.business.business.config.ExternalApiProperties;
import com.rxjava.business.business.model.PostsResponse;
import com.rxjava.business.business.model.proxy.Posts;
import com.rxjava.business.business.proxy.AuthenticatedUserProxy;
import com.rxjava.business.business.proxy.HttpClientService;
import com.rxjava.business.business.dao.PostsApiDao;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsApiDaoImpl implements PostsApiDao {
    private final ExternalApiProperties props;
    private final HttpClientService httpClientService;
    private final AuthenticatedUserProxy proxy;

    @Override
    public Single<PostsResponse> getFromApi1(Integer id) {
        return httpClientService.get(props.url(), id, PostsResponse.class);
    }

    @Override
    public Observable<Posts> getPostsById(Integer id) {
        return proxy.findById(id)
                .subscribeOn(Schedulers.io());
    }
}
