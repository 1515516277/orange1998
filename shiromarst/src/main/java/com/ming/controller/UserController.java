package com.ming.controller;

import com.ming.entity.CollectEntity;
import com.ming.entity.UserEntity;
import com.ming.realm.CustomRealm;
import com.ming.until.SpringContextUtil;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Controller
@RequestMapping("/UserController")
public class UserController {
    @Autowired
    SessionDAO sessionDAO;
    @Autowired
    CustomRealm customRealm;
    public static final String SESSION_STATUS ="sojson-online-status";
    //查询当前在线用户
    @RequestMapping("/onlineuser")
    public String onlineuser(Model model){
        try {
            model.addAttribute("user",session(null,0));
            return "onlineuser";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //踢出用户
    @RequestMapping("/outid")
    public void outid(int id){
        try {
            session(id,0);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public List<CollectEntity> session(Object obj,int status){
        List<CollectEntity> userEntities=new ArrayList<>();
        int i =0;
        try {
            //获取全部用户
            Collection<Session> activeSessions = sessionDAO.getActiveSessions();

            if(obj instanceof String){
                i=1;
            }else if(obj instanceof Integer){
                i=2;
            }
            for (Session x : activeSessions ) {
                //获取用户信息
                Object attribute = x.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                //转类型
                attribute= attribute instanceof SimplePrincipalCollection ?((SimplePrincipalCollection)attribute).getPrimaryPrincipal():null;
                if(null!=attribute && attribute instanceof UserEntity){
                    //转换类型
                    CollectEntity collectEntity=new CollectEntity((UserEntity) attribute);
                    switch (i){
                        case 0:
                            userEntities.add(collectEntities(collectEntity,x));
                            break;
                        case 1://String 类型
                            if(collectEntity.getUsername().equals(obj.toString())){
                                userEntities.add(collectEntities(collectEntity,x));
                            }
                            break;
                        case 2://int类型
                            if(collectEntity.getId() == (int)obj){
                                x.setTimeout(0);
                                x.stop();
                                x.removeAttribute(x.getId());
                                //activeSessions.clear();
                                //userEntities.add(collectEntities(collectEntity,x));
                            }
                    }

                }

            }
            return userEntities;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CollectEntity collectEntities(CollectEntity collectEntity,Session x){
        try {
            collectEntity.setLasttime(x.getLastAccessTime());
            collectEntity.setStarttime(x.getStartTimestamp());
            collectEntity.setSessionid(x.getId().toString());
            collectEntity.setStatus(x.getTimeout()>0?true:false);
            System.out.println(x.getAttribute(SESSION_STATUS));
            return collectEntity;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    //清除当前用户权限
    @RequestMapping("/CustomRealm")
    @ResponseBody
    public void CustomRealm(){
        try {
            //CustomRealm customRealm = (CustomRealm) SpringContextUtil.getBean("myRealm");
            customRealm.clearCached();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //清除其他用户权限信息
    @RequestMapping("/CustomRealmUser")
    @ResponseBody
    public void CustomRealmUser(){
        try {
            //CustomRealm customRealm = (CustomRealm) SpringContextUtil.getBean("myRealm");
            customRealm.clearCachedwithname("user");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
