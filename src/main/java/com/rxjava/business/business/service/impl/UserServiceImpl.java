package com.rxjava.business.business.service.impl;

import com.rxjava.business.business.dao.UserDao;
import com.rxjava.business.business.mapper.UserMapper;
import com.rxjava.business.business.model.request.UserRequest;
import com.rxjava.business.business.model.response.UserResponse;
import com.rxjava.business.business.service.UserService;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public Observable<UserResponse> getUserById(String id) {
        return userDao.getUserById(id)
                .map(userMapper::buildUserResponseFromUserEntity);
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
                    userMapper.updateEmailAndPhone(userRequest, existingUser);
                    return userDao.saveUser(existingUser);
                });
    }

    @Override
    public Completable deleteUser(String id) {
        return userDao.deleteUser(id);
    }
}
