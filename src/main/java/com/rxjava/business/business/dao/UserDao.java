package com.rxjava.business.business.dao;

import com.rxjava.business.business.model.entity.User;
import io.reactivex.Completable;
import io.reactivex.Observable;

public interface UserDao {
    Observable<User> getAllUsers();
    Completable saveUser(User user);
}
