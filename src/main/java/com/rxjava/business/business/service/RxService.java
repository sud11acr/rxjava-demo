package com.rxjava.business.business.service;

import io.reactivex.Observable;
import org.springframework.stereotype.Service;

@Service
public class RxService {

    public Observable<String> getGreetings() {
        return Observable.just("Hola", "¿cómo estás?", "¡Bienvenido a RxJava2!");
    }
}

