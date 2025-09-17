package com.rxjava.business.business.proxy;


import com.rxjava.business.business.model.proxy.Posts;
import io.reactivex.Maybe;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthenticatedUserProxy {

    @GET("{id}")
    Maybe<Posts> findById(@Path("id") Integer id);
}
