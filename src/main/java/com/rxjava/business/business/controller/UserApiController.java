package com.rxjava.business.business.controller;

import com.rxjava.business.business.model.request.UserRequest;
import com.rxjava.business.business.model.response.UserResponse;
import com.rxjava.business.business.service.UserService;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/api/users")
public class UserApiController {
    private final UserService service;


    @GetMapping
    public Maybe<ResponseEntity<Flowable<UserResponse>>> getAll() {
        log.info("Begin getAll - UserApiController");
        return Maybe.just(ResponseEntity.ok(service.getAllUsers()))
                .doOnSuccess(response ->
                        log.info("End getAll - UserApiController"));
    }

    @PostMapping
    public Maybe<ResponseEntity<Void>> save(@Valid @RequestBody UserRequest userRequest) {
        log.info("Begin save - UserApiController");

        return service.saveUser(userRequest)
                .andThen(Maybe.just(ResponseEntity.ok().<Void>build()))
                .doOnSuccess(response ->
                        log.info("End save - UserApiController"));
    }

}
