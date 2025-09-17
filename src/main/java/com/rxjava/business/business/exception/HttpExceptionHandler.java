package com.rxjava.business.business.exception;

import io.reactivex.Maybe;
import retrofit2.adapter.rxjava2.HttpException;
import org.springframework.stereotype.Component;

@Component
public class HttpExceptionHandler {
    public <T> Maybe<T> handleHttpException(Throwable error) {
        if (error instanceof HttpException httpEx) {
            int code = httpEx.code();
            switch (code) {
                case 404:
                    return Maybe.empty();
                case 400:
                    // Puedes lanzar una excepción personalizada si lo necesitas
                    return Maybe.error(new IllegalArgumentException("Solicitud inválida"));
                case 500:
                    return Maybe.error(new RuntimeException("Error interno del servidor"));
                default:
                    return Maybe.error(error);
            }
        }
        return Maybe.error(error);
    }
}