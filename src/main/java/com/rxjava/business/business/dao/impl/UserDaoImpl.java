package com.rxjava.business.business.dao.impl;

import com.rxjava.business.business.dao.UserDao;
import com.rxjava.business.business.model.entity.User;
import com.rxjava.business.business.repository.UserRepository;
import io.reactivex.Completable;
import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.adapter.rxjava.RxJava2Adapter;

import static io.reactivex.schedulers.Schedulers.io;

@RequiredArgsConstructor
@Slf4j
@Component
public class UserDaoImpl implements UserDao {
    private final UserRepository repository;

    @Override
    public Observable<User> getAllUsers() {
        return RxJava2Adapter.fluxToObservable(repository.findAll())
                .switchIfEmpty(Observable.empty())
                .subscribeOn(io())
                .doOnSubscribe(disposable -> log.info("Fetching all users from the database"));
    }

    @Override
    public Observable<User> getUserById(String id) {
        return RxJava2Adapter.monoToSingle(repository.findById(id))
                .toObservable()
                .switchIfEmpty(Observable.empty())
                .subscribeOn(io())
                .doOnSubscribe(disposable -> log.info("Fetching user with id: {}", id));
    }


    @Override
    public Completable saveUser(User user) {
        return RxJava2Adapter.monoToCompletable(repository.save(user))
                .subscribeOn(io())
                .doOnComplete(() -> log.info("User saved successfully: {}", user))
                .doOnError(error -> log.error("Error saving user: {}", user, error));
    }

    @Override
    public Observable<User> saveUserReturn(User user) {
        return RxJava2Adapter.monoToSingle(repository.save(user))
                .subscribeOn(io())
                .toObservable()
                .doOnNext(savedUser -> log.info("User saved successfully: {}", savedUser));
    }

    @Override
    public Completable updateUser(User user) {
        return null;
    }
}
