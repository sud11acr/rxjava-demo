package com.rxjava.business.business.proxy;


import com.rxjava.business.business.model.proxy.Cuenta;
import io.reactivex.Maybe;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthenticatedUserProxy {

    @GET("{id}")
    Maybe<Cuenta> findById(@Path("id") Integer id);
}
