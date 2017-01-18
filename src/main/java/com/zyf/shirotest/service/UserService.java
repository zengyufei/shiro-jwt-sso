package com.zyf.shirotest.service;

import com.zyf.shirotest.entity.User;

import java.io.Serializable;

public interface UserService {

	User queryInfo(Serializable id);

}
