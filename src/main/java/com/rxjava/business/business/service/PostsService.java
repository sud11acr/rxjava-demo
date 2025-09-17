package com.rxjava.business.business.service;

import com.rxjava.business.business.model.PostsResponse;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface PostsService {
    Single<PostsResponse> getAllPosts();
    Maybe<PostsResponse> getPostById(Integer id);
}
