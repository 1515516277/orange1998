package com.ming.realm;

import com.ming.entity.RoleEntity;
import com.ming.entity.UserEntity;
import com.ming.service.Roleservice;
import com.ming.service.Userservice;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {
    @Override
    public String getName() {
        return "CustomRealm";
    }

    @Autowired
    SessionDAO sessionDAO;
    @Autowired
    Userservice userservice;
    @Autowired
    Roleservice roleservice;
    //权限认证
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        try {
            //用户
            UserEntity user = (UserEntity) principalCollection.getPrimaryPrincipal();
            RoleEntity role=new RoleEntity();
            role.setUid(user.getId());
            //查询角色
            List<String> roleus=roleservice.selrolebyuid(role);
            //查询权限
            List<String> permission=roleservice.selpermissionbyuid(role);
            info.setStringPermissions(new HashSet(permission));
            info.setRoles(new HashSet(roleus));
        } catch (Exception e) {
           e.printStackTrace();
        }

        return info;
    }
    //登录认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //获取传入的账号密码
        String name= token.getUsername();
        String pass=new String(token.getPassword());

        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for(Session session:sessions){
            Object attribute = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            System.out.println(attribute);
            attribute= attribute instanceof SimplePrincipalCollection ?((SimplePrincipalCollection)attribute).getPrimaryPrincipal():null;
            if(null!=attribute && attribute instanceof UserEntity){
                UserEntity attribute11 = (UserEntity) attribute;
                String cc=attribute11.getUsername();
                if(name.equals(cc)) {
                    session.setTimeout(0);//设置session立即失效,即将其踢出系统
                    break;
                }
            }

        }

        //创建user对象
        UserEntity userEntity=new UserEntity();
        try {
            userEntity.setUsername(name);
            userEntity=userservice.selbyname(userEntity);
            //userEntity=JdbcResult.jdbc(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        //假设数据库密码
        //String password = "af602bdf325102f8e917cb77eb3e2d96";
        System.out.println("name:"+name+"  passwword:"+pass);
        if(userEntity==null){
            return null;
        }
        //盐
        ByteSource bytes = ByteSource.Util.bytes(name);
        AuthenticationInfo info = new SimpleAuthenticationInfo(userEntity,userEntity.getPassword(),bytes,getName());
        return info;
    }

    //清除缓存
    public void clearCached() {

        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
        System.out.println("ok");

    }
    //清除指定用户缓存
    public void clearCachedwithname(String name) {
        Collection<Session> activeSessions = sessionDAO.getActiveSessions();
        for (Session x:activeSessions) {
            Object attribute = x.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null!=attribute && attribute instanceof SimplePrincipalCollection ){
                SimplePrincipalCollection sim=((SimplePrincipalCollection) attribute);
                if(((UserEntity)sim.getPrimaryPrincipal()).getUsername().equals(name)){
                    super.clearCache(sim);
                }

            }
        }


    }

}
