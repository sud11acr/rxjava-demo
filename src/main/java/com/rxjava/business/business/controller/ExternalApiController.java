package com.rxjava.business.business.controller;

import com.rxjava.business.business.model.PostDto;
import com.rxjava.business.business.model.proxy.Cuenta;
import com.rxjava.business.business.service.ExternalApiService;
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
public class ExternalApiController {
    private final ExternalApiService service;

    @GetMapping("/posts/{id}")
    public Single<PostDto> consumeApi1(@PathVariable("id") Integer id) {
        return service.getFromApi1(id);
    }
    @GetMapping("/posts/v2/{id}")
    public Maybe<Cuenta> consumeApi2(@PathVariable("id") Integer id) {
        return service.getAccountById(id);
    }
}
