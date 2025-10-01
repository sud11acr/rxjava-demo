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
    public Completable saveUser(UserRequest userRequest) {
        return Observable.fromCallable(() -> userRequest)
                .map(userMapper::buildUserEntityFromUserRequest)
                .flatMapCompletable(userDao::saveUser);
    }
}
