package com.rxjava.business.business.proxy;


import com.rxjava.business.business.model.proxy.Cuenta;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface AuthenticatedUserProxy {

    @GET("/{id}")
    Maybe<Cuenta> findById(@Path("id") Integer id);

    @GET("/")
    Observable<List<Cuenta>> findAll();
}
