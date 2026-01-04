package com.ngk.authen.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ngk.authen.entities.UserEntity;
import com.ngk.authen.model.api.register.UserDTO;
import com.ngk.authen.model.api.register.UserRegisterRequest;

@Mapper
public interface UserMapper {
	UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
	@Mapping(ignore = true, target = "password")
	UserEntity toUserEntity(UserRegisterRequest request);
	
	@Mapping(target = "name", source = "username")
	UserDTO toUserRegisterResponse(UserEntity enity);
}
