package com.rxjava.business.business.proxy;


import com.rxjava.business.business.model.proxy.Posts;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthenticatedUserProxy {

    @GET("{id}")
    Observable<Posts> findById(@Path("id") Integer id);
}
