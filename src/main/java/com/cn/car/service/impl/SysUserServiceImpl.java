package com.cn.car.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.car.dao.SysUserDao;
import com.cn.car.entity.SysUser;
import com.cn.car.service.SysUserService;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserDao sysUserDao;
	@Override
	public List<SysUser> userlist(SysUser user) {
		return sysUserDao.userlist(user);
	}
	@Override
	public int userlistcount(SysUser user) {
		return sysUserDao.userlistcount(user);
	}
	
	/**
     * 微信查询用户是否绑定姓名
     * @param openId
     * @return
     */
	@Override
	public SysUser checkUser(String openId) {
		// TODO Auto-generated method stub
		return sysUserDao.checkUser(openId);
	}
	 /**
     * 绑定微信和账号
     * @param user
     * @return
     */
	@Override
    public int blindUser(SysUser user) {
		return sysUserDao.blindUser(user);
	}
	/**
     * 登录
     * @param user
     * @return
     */
	@Override
	public int loginUser(SysUser user) {
		return sysUserDao.loginUser(user);
	}
}
