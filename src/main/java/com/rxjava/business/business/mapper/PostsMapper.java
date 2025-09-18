package com.rxjava.business.business.mapper;

import com.rxjava.business.business.model.dto.PostsDto;
import com.rxjava.business.business.model.response.PostsResponse;
import com.rxjava.business.business.model.proxy.Posts;
import com.rxjava.business.business.model.response.PostsResponseList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostsMapper {
    PostsResponse buildPostsResponseFromPosts(Posts posts);
    PostsResponseList buildPostsResponseListFromPostsList(PostsDto postsList);
}
