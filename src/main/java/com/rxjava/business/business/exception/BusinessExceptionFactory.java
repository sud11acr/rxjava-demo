package com.rxjava.business.business.exception;

import com.rxjava.business.business.config.ErrorProperties;
import com.rxjava.business.business.model.error.BusinessError;
import com.rxjava.business.business.model.proxy.Cuenta;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.HttpException;

import java.util.Objects;

@AllArgsConstructor
@Component
@Slf4j
public class BusinessExceptionFactory {

    private final ErrorProperties errorProperties;

    public Maybe<Cuenta> handleErrorCuenta(Throwable throwable) {
        return Maybe.error(createBusinessException(throwable));
    }

    private ApiException createBusinessException(Throwable throwable) {
        String systemCode = "500";
        if(Objects.nonNull(throwable) && throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            systemCode = String.valueOf(httpException.code());
        }
        log.info("BusinessError : {}", errorProperties.getBusinessError());
        BusinessError businessError = errorProperties
                .getBusinessError().get(systemCode);

        return ApiException.builder()
                .systemCode(businessError.getCode())
                .description(businessError.getDescription())
                .category(ErrorCategory.valueOf(businessError.getErrorCategory()))
                .cause(throwable)
                .markAsResolved()
                .build();
    }
}
