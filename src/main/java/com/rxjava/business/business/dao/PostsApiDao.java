package com.rxjava.business.business.dao;

import com.rxjava.business.business.model.PostsResponse;
import com.rxjava.business.business.model.proxy.Posts;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface PostsApiDao {
    public Single<PostsResponse> getFromApi1(Integer id);
    Maybe<Posts> getPostsById(Integer id);
}
