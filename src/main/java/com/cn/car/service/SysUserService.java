package com.cn.car.service;

import java.util.List;

import com.cn.car.entity.SysUser;

public interface SysUserService {
	public List<SysUser> userlist(SysUser user);
	public int userlistcount(SysUser user);
}

