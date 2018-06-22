package com.ming.service;

import com.ming.entity.RoleEntity;
import com.ming.entity.UserEntity;

import java.util.List;

public interface Roleservice {
    List<String> selrolebyuid(RoleEntity roleEntity);
    List<String> selpermissionbyuid(RoleEntity roleEntity);
}
