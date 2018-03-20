package com.cn.car.service;

import com.cn.car.entity.User;


public interface UserService {
	/**
	 * 用户列表
	 * @return
	 */
	public int selectUser(User user);
}
