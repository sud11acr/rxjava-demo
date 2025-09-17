package com.rxjava.business.business.dao;

import com.rxjava.business.business.model.PostsResponse;
import com.rxjava.business.business.model.proxy.Posts;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface PostsApiDao {
    Single<PostsResponse> getFromApi1(Integer id);
    Observable<Posts> getPostsById(Integer id);
}
