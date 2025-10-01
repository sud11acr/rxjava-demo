package com.rxjava.business.business.service;

import com.rxjava.business.business.model.request.UserRequest;
import com.rxjava.business.business.model.response.UserResponse;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface UserService {
    Flowable<UserResponse> getAllUsers();
    Completable saveUser(UserRequest userRequest);
}
