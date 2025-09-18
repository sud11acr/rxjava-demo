package com.rxjava.business.business.proxy;


import com.rxjava.business.business.model.proxy.Posts;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface JsonPlaceHolderProxy {

    @GET("/posts/{id}")
    Observable<Posts> findById(@Path("id") Integer id);
    @GET("/posts")
    Observable<List<Posts>> findAll();
}
