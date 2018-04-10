package com.cn.car.service;

import java.util.List;

import com.cn.car.entity.SysUser;

public interface SysUserService {
	/**
	 * 查询用户列表 
	 * @param user
	 * @return
	 */
	public List<SysUser> userlist(SysUser user);
	/**
	 * 用户列表计数
	 * @param user
	 * @return
	 */
	public int userlistcount(SysUser user);
	/**
     * 微信查询用户是否绑定姓名
     * @param openId
     * @return
     */
    public SysUser checkUser(String openId);
    /**
     * 绑定微信和账号
     * @param user
     * @return
     */
    public int blindUser(SysUser user);
    /**
     * 登录
     * @param user
     * @return
     */
    public int loginUser(SysUser user);
}

