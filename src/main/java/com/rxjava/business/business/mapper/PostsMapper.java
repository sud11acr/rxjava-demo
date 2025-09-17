package com.rxjava.business.business.mapper;

import com.rxjava.business.business.model.PostsResponse;
import com.rxjava.business.business.model.proxy.Posts;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostsMapper {
    PostsResponse buildPostsResponseFromPosts(Posts posts);
}
