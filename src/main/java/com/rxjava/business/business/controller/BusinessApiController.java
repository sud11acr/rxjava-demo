package com.rxjava.business.business.controller;

import com.rxjava.business.business.model.PostsResponse;
import com.rxjava.business.business.model.proxy.Posts;
import com.rxjava.business.business.dao.PostsApiDao;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/external-api")
public class BusinessApiController {
    private final PostsApiDao service;

    @GetMapping("/posts/{id}")
    public Single<PostsResponse> consumeApi1(@PathVariable("id") Integer id) {
        return service.getFromApi1(id);
    }
    @GetMapping("/posts/v1/{id}")
    public Maybe<Posts> getPostsById(@PathVariable("id") Integer id) {
        return service.getPostsById(id);
    }
}
