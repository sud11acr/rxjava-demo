package com.rxjava.business.business.service;

import com.rxjava.business.business.model.request.UserRequest;
import com.rxjava.business.business.model.response.UserResponse;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface UserService {
    Flowable<UserResponse> getAllUsers();
    Completable saveUser(UserRequest userRequest);
    Observable<UserResponse> saveUserReturn(UserRequest userRequest);
    Completable updateUser(UserRequest userRequest,String id);
    Completable updatePartialUser(UserRequest userRequest, String id);
    Completable deleteUser(String id);
}
