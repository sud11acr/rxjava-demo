package com.rxjava.business.business.controller;

import com.rxjava.business.business.model.PostsResponse;
import com.rxjava.business.business.service.PostsService;
import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/external-api")
public class BusinessApiController {
    private final PostsService service;

    @GetMapping("/posts/v1/{id}")
    public Maybe<PostsResponse> getPostsById(@PathVariable("id") Integer id) {
        return service.getPostById(id);
    }
}
