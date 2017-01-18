package com.github.zhangkaitao.shiro.chapter20.realm;

import com.github.zhangkaitao.shiro.chapter20.codec.HmacSHA256Utils;
import com.zyf.shirotest.entity.User;
import com.zyf.shirotest.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-26
 * <p>Version: 1.0
 */
public class StatelessRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof UsernamePasswordToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //根据用户名查找角色，请根据需求实现
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();
        authorizationInfo.addRole(user.getRoleName());
        authorizationInfo.addStringPermission(user.getPermission());
        return authorizationInfo;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken statelessToken = (UsernamePasswordToken) token;
        String username = statelessToken.getUsername();
        char[] password = statelessToken.getPassword();
        User user = userService.queryInfo(1);
        if (!user.getUserName().equals(username)) {
            throw new UnknownAccountException("没有这个账号");
        }
        if (!user.getPassword().equals(String.valueOf(password))) {
            throw new UnknownError("密码错误");
        }
        return new SimpleAuthenticationInfo(
                user,
                username,
                getName());
    }

}
