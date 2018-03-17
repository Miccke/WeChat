package com.cn.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.car.dao.UserDao;
import com.cn.car.entity.User;
import com.cn.car.service.UserService;
@Service("UserService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	public int selectUser(User user) {
		return userDao.selectUser(user);
	}

}
