package com.rxjava.business.business.service.impl;

import com.rxjava.business.business.dao.UserDao;
import com.rxjava.business.business.exception.BaseException;
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
                .toFlowable(BackpressureStrategy.BUFFER)
                .doOnError(e -> log.error("Error retrieving all users: {}", e.getMessage(), e))
                .doOnComplete(() -> log.info("Successfully retrieved all users"));
    }

    @Override
    public Observable<UserResponse> getUserById(String id) {
        return userDao.getUserById(id)
                .map(userMapper::buildUserResponseFromUserEntity)
                .doOnError(e -> log.error("Error retrieving user {}: {}", id, e.getMessage(), e));
    }

    @Override
    public Completable saveUser(UserRequest userRequest) {
        return Observable.fromCallable(() -> userRequest)
                .map(userMapper::buildUserEntityFromUserRequest)
                .flatMapCompletable(userDao::saveUser)
                .doOnComplete(() -> log.info("User saved successfully"))
                .doOnError(e -> log.error("Error saving user: {}", e.getMessage(), e));
    }

    @Override
    public Observable<UserResponse> saveUserReturn(UserRequest userRequest) {
        return Observable.just(userRequest)
                .map(userMapper::buildUserEntityFromUserRequest)
                .flatMap(userDao::saveUserReturn)
                .map(userMapper::buildUserResponseFromUserEntity)
                .doOnError(e ->
                        log.error("Error saving and returning user: {}", e.getMessage(), e));

    }

    @Override
    public Completable updateUser(UserRequest userRequest, String id) {
        return Observable.just(userRequest)
                .map(userMapper::buildUserEntityFromUserRequest)
                .flatMapCompletable(user -> {
                    user.setId(id);
                    return userDao.saveUser(user);
                })
                .doOnComplete(() -> log.info("User {} updated successfully", id))
                .doOnError(e ->
                        log.error("Error updating user {}: {}", id, e.getMessage(), e));
    }

    @Override
    public Completable updatePartialUser(UserRequest userRequest, String id) {
        return userDao.getUserById(id)
                .flatMapCompletable(existingUser -> {
                    userMapper.updateEmailAndPhone(userRequest, existingUser);
                    return userDao.saveUser(existingUser);
                })
                .doOnComplete(() -> log.info("User {} partially updated successfully", id))
                .doOnError(e ->
                        log.error("Error partially updating user {}: {}", id, e.getMessage(), e));
    }

    @Override
    public Completable deleteUser(String id) {
        return userDao.getUserById(id)
                .switchIfEmpty(Observable.error(
                        new BaseException("Not found user with id: " + id)))
                .flatMapCompletable(user -> userDao.deleteUser(id))
                .doOnComplete(() -> log.info("User {} deleted successfully", id))
                .doOnError(e -> log.error("Error deleting user {}: {}", id, e.getMessage(), e));

    }
}
