package com.ming.shiro.shiros;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShiroTest {

    @Test
    public void reles() throws Exception {
        //加载配置文件 创建工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-password.ini");
        //获取securitymanage实例
        SecurityManager securityManager = factory.getInstance();
        //绑定SecurityUnils
        SecurityUtils.setSecurityManager(securityManager);
        //得到主体
        Subject subject = SecurityUtils.getSubject();
        //创建用户密码

        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
        try {
            //登录验证
            subject.login(token);

        } catch (UnknownAccountException e) {
            System.out.println("用户错误");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        }catch (AuthenticationException e){
            System.out.println("账号或密码错误");
        }
        System.out.println("是否登录：" + subject.isAuthenticated());


        //是否有admin权限
        List<String> list=new ArrayList<String>();
        list.add("admin");
        list.add("user");
        boolean adminss = subject.hasAllRoles(list);
        boolean[] admins = subject.hasRoles(list);
        System.out.println(Arrays.asList(adminss));
        for (int i =0;i<admins.length;i++){
            System.out.println(admins[i]);
        }


    }
}
