package com.rxjava.business.business.service.impl;

import com.rxjava.business.business.dao.UserDao;
import com.rxjava.business.business.mapper.UserMapper;
import com.rxjava.business.business.model.entity.User;
import com.rxjava.business.business.model.request.UserRequest;
import com.rxjava.business.business.model.response.UserResponse;
import com.rxjava.business.business.service.UserService;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;

    @Override
    public Flowable<UserResponse> getAllUsers() {
        return userDao.getAllUsers()
                .map(userMapper::buildUserResponseFromUserEntity)
                .toFlowable(BackpressureStrategy.BUFFER);
    }

    @Override
    public Completable saveUser(UserRequest userRequest) {
        return Observable.fromCallable(() -> userRequest)
                .map(userMapper::buildUserEntityFromUserRequest)
                .flatMapCompletable(userDao::saveUser);
    }

    @Override
    public Observable<UserResponse> saveUserReturn(UserRequest userRequest) {
        return Observable.just(userRequest)
                .map(userMapper::buildUserEntityFromUserRequest)
                .flatMap(userDao::saveUserReturn)
                .map(userMapper::buildUserResponseFromUserEntity);
    }

    @Override
    public Completable updateUser(UserRequest userRequest, String id) {
        return Observable.just(userRequest)
                .map(userMapper::buildUserEntityFromUserRequest)
                .flatMapCompletable(user -> {
                    user.setId(id);
                    return userDao.saveUser(user);
                });
    }

    @Override
    public Completable updatePartialUser(UserRequest userRequest, String id) {
        return userDao.getUserById(id)
                .flatMapCompletable(existingUser -> {
                    buildUser(userRequest, existingUser);
                    return userDao.saveUser(existingUser);
                });
    }

    private static void buildUser(UserRequest userRequest, User existingUser) {
        Optional.ofNullable(userRequest.getEmail())
                .ifPresent(email -> {
                    if (StringUtils.isNotBlank(email)) {
                        existingUser.setEmail(email);
                    }
                });
        Optional.ofNullable(userRequest.getPhone())
                .ifPresent(phone -> {
                    if (StringUtils.isNotBlank(phone)) {
                        existingUser.setPhone(phone);
                    }
                });
    }
}
