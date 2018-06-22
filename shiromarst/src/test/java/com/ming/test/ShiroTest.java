package com.ming.test;

import com.ming.entity.UserEntity;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ShiroTest {
    @Test
    public void simplmd5() {
        String type="md5";
        String name="123";
        String salt="zhangsan";//盐
        int hashiterations=5;
        String admin="admin";
        SimpleHash hash = new SimpleHash("md5","admin","admin",1);
        Md5Hash md5Hash = new Md5Hash("123",null,1);
        System.out.println(hash);
        System.out.println(md5Hash);
    }

    @Test
    public void user(){
        UserEntity u=new UserEntity();
        u.setPassword("xxx");
        u.setUsername("www");
        System.out.println(StringUtils.isEmpty(u.toString()));
        UserEntity c=new UserEntity();
        System.out.println(StringUtils.isEmpty(c.getPassword()));
    }
    @Test
    public void list(){
        int[] a={1,2,3};
        ArrayList<Boolean> list=new ArrayList<Boolean>();
        list.add(true);
        list.add(true);
        list.add(true);
        System.out.println(Arrays.asList(list));
    }
}
