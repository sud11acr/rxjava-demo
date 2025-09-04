package com.rxjava.business.business.controller;

import com.rxjava.business.business.model.PostDto;
import com.rxjava.business.business.service.ExternalApiService;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ExternalApiController {
    private final ExternalApiService service;

    @GetMapping("/consume-api1")
    public Single<PostDto> consumeApi1() {
        return service.getFromApi1();
    }
}
