package com.ming.dao;

import com.ming.entity.RoleEntity;
import com.ming.entity.UserEntity;

import java.util.List;


public interface RoleEntityMapper {

	List<String> selrolebyuid(RoleEntity roleEntity);
	List<String> selpermissionbyuid(RoleEntity roleEntity);
}