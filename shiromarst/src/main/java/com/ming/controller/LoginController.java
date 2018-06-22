package com.ming.controller;

import com.ming.entity.CollectEntity;
import com.ming.entity.UserEntity;
import com.ming.realm.CustomRealm;
import com.ming.service.Userservice;
import com.ming.until.Msg;
import com.ming.until.SpringContextUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    Userservice userservice;


    @RequestMapping
    public String login() {
        return "login";
    }

    //登录
    @RequestMapping("/login")
    @ResponseBody
    public Msg volidate(UserEntity userEntity, HttpServletRequest request)  {
        Msg msg=Msg.fail();
        String result = "redirect:/login";
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getUsername(), userEntity.getPassword());
            Subject subject = SecurityUtils.getSubject();

            token.setRememberMe(true);
            subject.login(token);
            System.out.println(token.isRememberMe());
            msg=Msg.success().add("url","/emp/emplist");
        }catch (UnknownAccountException e){
            System.out.println("用户名错误");
            msg=Msg.fail().add("code","-1");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
            msg=Msg.fail().add("code","-2");
        }catch (Exception e) {
            e.printStackTrace();
            msg=Msg.fail();
        } finally {
            return msg;
        }
    }

    //注册
    @RequestMapping("/register")
    @ResponseBody
    public Msg register(UserEntity userEntity) {
        try {
            if (userservice.selbyname(userEntity) == null) {
                int i = userservice.adduser(userEntity);

                return i>0?Msg.success().add("url","/login"):Msg.fail().add("code","-3");
            }
           return Msg.fail().add("code","-2");
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("code","-1");
        }
    }

    //退出
    @RequestMapping("/logout")
    public void logout(){
    }




}
