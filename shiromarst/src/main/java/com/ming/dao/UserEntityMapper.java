package com.ming.dao;

import com.ming.entity.UserEntity;


public interface UserEntityMapper {

	int login(UserEntity userEntity);

	UserEntity selbyname(UserEntity userEntity);

	int adduser(UserEntity userEntity);
}