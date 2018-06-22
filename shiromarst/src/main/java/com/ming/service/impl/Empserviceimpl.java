package com.ming.service.impl;

import com.ming.dao.EmpEntityMapper;
import com.ming.entity.EmpEntity;
import com.ming.service.Empservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Empserviceimpl implements Empservice {

    @Autowired
    EmpEntityMapper empmapper;

    @Cacheable("empEntity")
    public List<EmpEntity> selectall() {
        List<EmpEntity> list=empmapper.selectall();
        System.out.println("没有走缓存");
        return list;
    }

    public int insertemp(EmpEntity emp){

        return empmapper.insertemp(emp);
    }

    public int delemp(int id) {

        return empmapper.delemp(id);
    }
    @Override
    public String t() {
        return "oxo";
    }

}
