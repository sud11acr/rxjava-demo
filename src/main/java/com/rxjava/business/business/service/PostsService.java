package com.rxjava.business.business.service;

import com.rxjava.business.business.model.dto.PostsDto;
import com.rxjava.business.business.model.response.PostsResponse;
import com.rxjava.business.business.model.response.PostsResponseList;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface PostsService {
    Maybe<PostsResponseList> getAllPosts();
    Observable<PostsResponse> getAllPostsV2();
    Maybe<PostsResponse> getPostById(Integer id);
}
