package com.rxjava.business.business.controller;

import static com.rxjava.business.business.utils.TestUtils.generateListOrObject;

import com.rxjava.business.business.model.response.UserResponse;
import com.rxjava.business.business.service.UserService;
import io.reactivex.Flowable;
import io.reactivex.observers.TestObserver;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserApiControllerTest {

    @InjectMocks
    private UserApiController controller;

    @Mock
    private UserService service;

    @Test
    @DisplayName("return success when get all users")
    void returnSuccessfulWhenGetAllUser() throws IOException {
        UserResponse userResponse = generateListOrObject(
                "mock/UserResponseMock.json", UserResponse.class);

        Mockito.when(service.getAllUsers())
                .thenReturn(Flowable.just(userResponse));

        TestObserver<ResponseEntity<Flowable<UserResponse>>> testObserver =
                controller.getAll().test();
        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        testObserver.assertValue(response -> response.getStatusCode().is2xxSuccessful());

    }

}
