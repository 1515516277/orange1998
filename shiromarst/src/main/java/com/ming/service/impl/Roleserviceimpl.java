package com.ming.service.impl;

import com.ming.dao.RoleEntityMapper;
import com.ming.entity.RoleEntity;
import com.ming.service.Roleservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Roleserviceimpl implements Roleservice {

    @Autowired
    private RoleEntityMapper roleEntityMapper;
    @Override
    public List<String> selrolebyuid(RoleEntity roleEntity) {
        return roleEntityMapper.selrolebyuid(roleEntity);
    }

    @Override
    public List<String> selpermissionbyuid(RoleEntity roleEntity) {
        return roleEntityMapper.selpermissionbyuid(roleEntity);
    }
}
