package com.rxjava.business.business.mapper;

import com.rxjava.business.business.model.PostDto;
import com.rxjava.business.business.model.proxy.Cuenta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    PostDto buildPostDtoFromAccount(Cuenta account);
}
