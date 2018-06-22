package com.ming.service.impl;

import com.ming.dao.UserEntityMapper;
import com.ming.entity.UserEntity;
import com.ming.service.Userservice;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class Userserviceimpl implements Userservice {

    @Autowired
    UserEntityMapper userEntityMapper;

    public int login(UserEntity userEntity) {
        return userEntityMapper.login(userEntity);
    }

    public UserEntity selbyname(UserEntity userEntity) {
        return userEntityMapper.selbyname(userEntity);
    }

    @Override
    public int adduser(UserEntity userEntity) {
        if(!StringUtils.isEmpty(userEntity.getPassword())){
            String newpwd=md5pwd(userEntity.getPassword(),userEntity.getUsername());
            userEntity.setPassword(newpwd);
        }
        return userEntityMapper.adduser(userEntity);
    }

    public String md5pwd(String pwd,String name){
        Md5Hash md5Hash=new Md5Hash(pwd,name,1);
        return md5Hash.toString();
    }
}
