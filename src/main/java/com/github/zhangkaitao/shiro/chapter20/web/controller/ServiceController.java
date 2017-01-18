package com.github.zhangkaitao.shiro.chapter20.web.controller;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.Token;
import com.baomidou.kisso.annotation.Permission;
import com.zyf.shirotest.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ServiceController {

    @RequestMapping("/hello")
    public String hello1(HttpServletRequest request, HttpServletResponse response) {
        return "hello ";
    }

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        Token token = SSOHelper.attrToken(request);
        return "index";
    }


    @RequestMapping("login")
    public String login(
            String username, String password, String ReturnURL,
            HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        //4、生成无状态Token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //5、委托给Realm进行登录
            subject.login(token);
            User user = (User) subject.getPrincipal();
             /*
			 * authSSOCookie 设置 cookie 同时改变 jsessionId
			 */
            SSOToken st = new SSOToken(request);
            st.setId(user.getId());

            //记住密码，设置 cookie 时长 1 周 = 604800 秒 【动态设置 maxAge 实现记住密码功能】
            //String rememberMe = req.getParameter("rememberMe");
            //if ( "on".equals(rememberMe) ) {
            //	request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 604800);
            //}
            SSOHelper.setSSOCookie(request, response, st, true);

			/*
			 * 登录需要跳转登录前页面，自己处理 ReturnURL 使用
			 * HttpUtil.decodeURL(xx) 解码后重定向
			 */
        } catch (Exception e) {
            e.printStackTrace();
            return "login error"; //6、登录失败
        }
        return "redirect:index";
    }

}
