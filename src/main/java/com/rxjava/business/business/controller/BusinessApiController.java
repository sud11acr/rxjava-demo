package com.rxjava.business.business.controller;

import com.rxjava.business.business.model.response.PostsResponse;
import com.rxjava.business.business.model.response.PostsResponseList;
import com.rxjava.business.business.service.PostsService;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/external-api")
@Slf4j
public class BusinessApiController {
    private final PostsService service;

    @GetMapping("/posts/v1/{id}")
    public Maybe<ResponseEntity<PostsResponse>> getPostsById(@PathVariable("id") Integer id) {
        return service.getPostById(id)
                .map(ResponseEntity::ok)
                .doOnSuccess(response -> log.info("Post con id {} obtenido con éxito", id))
                .doFinally(() -> log.info("Finalizado el procesamiento para el post con id {}", id));
    }

    @GetMapping("/posts/v1")
    public Maybe<ResponseEntity<PostsResponseList>> getAllPosts() {
        return service.getAllPosts()
                .map(ResponseEntity::ok)
                .doOnSuccess(response -> log.info("Todos los posts obtenidos con éxito"))
                .doFinally(() -> log.info("Finalizado el procesamiento para obtener todos los posts"));
    }

    @GetMapping("/posts/v2")
    public Maybe<ResponseEntity<Flowable<PostsResponse>>> getAllPostsV2() {
        return service.getAllPostsV2()
                .toList()
                .toMaybe()
                .map(posts -> ResponseEntity.ok(Flowable.fromIterable(posts)))
                .doOnSuccess(response -> log.info("Todos los posts (v2) obtenidos con éxito"));
    }

}
