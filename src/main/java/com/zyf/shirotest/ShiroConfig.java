package com.zyf.shirotest;

import com.github.zhangkaitao.shiro.chapter20.filter.StatelessAuthcFilter;
import com.github.zhangkaitao.shiro.chapter20.mgt.StatelessDefaultSubjectFactory;
import com.github.zhangkaitao.shiro.chapter20.realm.StatelessRealm;
import com.zyf.shirotest.service.UserService;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

	@Autowired
	private UserService userService;

	@Bean
	public StatelessRealm getStatelessRealm() {
		StatelessRealm realm = new StatelessRealm();
		realm.setCachingEnabled(false);
		return realm;
	}
	@Bean
	public StatelessDefaultSubjectFactory getStatelessDefaultSubjectFactory() {
		return new StatelessDefaultSubjectFactory();
	}
	@Bean("sessionManager")
	public DefaultSessionManager getDefaultSessionManager() {
		DefaultSessionManager defaultSessionManager = new DefaultSessionManager();
		defaultSessionManager.setSessionValidationSchedulerEnabled(false);
		return defaultSessionManager;
	}
	@Bean("securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager() {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setRealm(getStatelessRealm());
		defaultWebSecurityManager.setSubjectFactory(getStatelessDefaultSubjectFactory());
		defaultWebSecurityManager.setSessionManager(getDefaultSessionManager());
		SessionStorageEvaluator sessionStorageEvaluator = ((DefaultSubjectDAO) defaultWebSecurityManager.getSubjectDAO()).getSessionStorageEvaluator();
		DefaultSessionStorageEvaluator sessionStorageEvaluator1 = (DefaultSessionStorageEvaluator) sessionStorageEvaluator;
		sessionStorageEvaluator1.setSessionStorageEnabled(false);
		return defaultWebSecurityManager;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(getDefaultWebSecurityManager());
		bean.setLoginUrl("/login.jsp");
		bean.setSuccessUrl("/index");
		bean.setUnauthorizedUrl("/403");
		Map<String, Filter> filters = new HashMap<>();
		filters.put("statelessAuthc", getStatelessAuthcFilter());
		bean.setFilters(filters);
		String urls =
				"/=anon\n" +
				"/favicon.ico=anon\n" +
				"*.html=anon\n" +
				"*.css=anon\n" +
				"*.js=anon\n" +
				"/login*=anon\n" +
				"/static/**=anon\n" +
				"/**=statelessAuthc\n";
		bean.setFilterChainDefinitions(urls);
		return bean;
	}

	public StatelessAuthcFilter getStatelessAuthcFilter() {
		StatelessAuthcFilter statelessAuthcFilter = new StatelessAuthcFilter();
		statelessAuthcFilter.setUserService(userService);
		return statelessAuthcFilter;
	}

/*
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}*/

	/**
	 *  开启shiro aop注解支持.
	 *  使用代理方式;所以需要开启代码支持;
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(getDefaultWebSecurityManager());
		return authorizationAttributeSourceAdvisor;
	}
}