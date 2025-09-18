package com.rxjava.business.business.dao.impl;

import com.rxjava.business.business.config.ExternalApiProperties;
import com.rxjava.business.business.model.dto.PostsDto;
import com.rxjava.business.business.model.proxy.Posts;
import com.rxjava.business.business.proxy.JsonPlaceHolderProxy;
import com.rxjava.business.business.dao.PostsApiDao;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsApiDaoImpl implements PostsApiDao {
    private final ExternalApiProperties props;
    private final JsonPlaceHolderProxy proxy;

    @Override
    public Observable<Posts> getPostsById(Integer id) {
        return proxy.findById(id)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<PostsDto> getAllPosts() {
        return Single.fromObservable(proxy.findAll())
                .subscribeOn(Schedulers.io())
                .map(post -> PostsDto.builder().postsList(post).build())
                .doOnSuccess(response -> log.info("PostsDto: {}", response.toString()))
                .doOnError(error -> log.error("Error fetching posts", error));
    }

    @Override
    public Observable<Posts> getAllPostsV2() {
        return proxy.findAll()
                //.flatMapIterable(posts -> posts)
                .flatMap(Observable::fromIterable)
                .subscribeOn(Schedulers.io());
    }
}
