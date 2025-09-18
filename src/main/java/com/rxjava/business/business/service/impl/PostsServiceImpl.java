package com.rxjava.business.business.service.impl;

import com.rxjava.business.business.dao.PostsApiDao;
import com.rxjava.business.business.mapper.PostsMapper;
import com.rxjava.business.business.model.response.PostsResponse;
import com.rxjava.business.business.model.response.PostsResponseList;
import com.rxjava.business.business.service.PostsService;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsServiceImpl implements PostsService {
    private final PostsApiDao postsApiDao;
    private final PostsMapper postsMapper;
    @Override
    public Maybe<PostsResponseList> getAllPosts() {
        return postsApiDao.getAllPosts()
                .map(postsMapper::buildPostsResponseListFromPostsList)
                .toMaybe()
                .doOnSuccess(success -> log.info("Posts obtenidos con éxito"))
                .doOnError(error -> log.error("Error obteniendo posts", error));
    }

    @Override
    public Observable<PostsResponse> getAllPostsV2() {
        return postsApiDao.getAllPostsV2()
                .map(postsMapper::buildPostsResponseFromPosts)
                .doOnNext(success -> log.info("Post obtenido con éxito"))
                .doOnError(error -> log.error("Error obteniendo posts", error));
    }

    @Override
    public Maybe<PostsResponse> getPostById(Integer id) {
        return postsApiDao.getPostsById(id)
                .map(postsMapper::buildPostsResponseFromPosts)
                .firstElement();
    }
}
