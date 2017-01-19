package com.github.zhangkaitao.shiro.chapter20.filter;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.Token;
import com.github.zhangkaitao.shiro.chapter20.mgt.StatelessDefaultSubjectFactory;
import com.zyf.shirotest.entity.User;
import com.zyf.shirotest.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-26
 * <p>Version: 1.0
 */
public class StatelessAuthcFilter extends AccessControlFilter {

    private UserService userService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("过滤器");
        HttpServletRequest req = (HttpServletRequest) request;
        Token token = SSOHelper.getToken(req);
        if(token != null){
            User user = userService.queryInfo(token.getId());
            SimplePrincipalCollection principalCollection =
                    new SimplePrincipalCollection(user, user.getUserName());
            SecurityManager securityManager = SecurityUtils.getSecurityManager();
            WebDelegatingSubject webDelegatingSubject =
                    new WebDelegatingSubject(principalCollection, true,
                            getHost(request), null, false,
                            request,response, securityManager);
            ThreadContext.bind(webDelegatingSubject);
            req.setAttribute("cacheToken", token);
        }else{
            redirectToLogin(request, response);
        }
        return true;
    }

    @Override
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        super.saveRequestAndRedirectToLogin(request, response);
    }

    //登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }

    protected String getHost(ServletRequest request) {
        return request.getRemoteHost();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
