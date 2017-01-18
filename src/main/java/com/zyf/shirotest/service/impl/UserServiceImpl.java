package com.zyf.shirotest.service.impl;

import com.zyf.shirotest.entity.User;
import com.zyf.shirotest.service.UserService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by zengyufei on 2017/1/18/018.
 * QQ: 312636208
 */
@Service
public class UserServiceImpl implements UserService{

	@Override
	public User queryInfo(Serializable id) {
		User user = new User();
		user.setId(123);
		user.setUserName("admin");
		user.setPassword("admin");
		user.setRealName("管理员");
		user.setAge(18);
		user.setRoleName("admin");
		user.setPermission("sys:user:*");
		return user;
	}
}
