package com.rxjava.business.business.dao;

import com.rxjava.business.business.model.dto.PostsDto;
import com.rxjava.business.business.model.proxy.Posts;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface PostsApiDao {
    Observable<Posts> getPostsById(Integer id);
    Single<PostsDto> getAllPosts();
    Observable<Posts> getAllPostsV2();
}
