package com.rxjava.business.business.service.impl;

import com.rxjava.business.business.dao.PostsApiDao;
import com.rxjava.business.business.mapper.PostsMapper;
import com.rxjava.business.business.model.PostsResponse;
import com.rxjava.business.business.service.PostsService;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {
    private final PostsApiDao postsApiDao;
    private final PostsMapper postsMapper;
    @Override
    public Single<PostsResponse> getAllPosts() {
        return null;
    }

    @Override
    public Maybe<PostsResponse> getPostById(Integer id) {
        return postsApiDao.getPostsById(id)
                .map(postsMapper::buildPostsResponseFromPosts)
                .firstElement();
    }
}
