package com.rxjava.business.business.controller;

import com.rxjava.business.business.service.RxService;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.adapter.rxjava.RxJava2Adapter;
import reactor.core.publisher.Flux;



@RestController
public class RxController {

    @Autowired
    private RxService rxService;

    @GetMapping("/saludos")
    public Flux<String> obtenerSaludos() {
        Observable<String> observable = rxService.getGreetings();
        return RxJava2Adapter.observableToFlux(observable, BackpressureStrategy.BUFFER);
    }
}