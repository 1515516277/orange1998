package com.ming.service;

import com.ming.entity.UserEntity;

public interface Userservice {
    int login(UserEntity userEntity);

    UserEntity selbyname(UserEntity userEntity);

    int adduser(UserEntity userEntity);
}
