package com.rxjava.business.business.mapper;

import com.rxjava.business.business.model.entity.User;
import com.rxjava.business.business.model.request.UserRequest;
import com.rxjava.business.business.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse buildUserResponseFromUserEntity(User user);
    User buildUserEntityFromUserRequest(UserRequest userRequest);
}
