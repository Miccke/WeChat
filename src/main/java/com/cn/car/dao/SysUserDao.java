package com.cn.car.dao;

import java.util.List;

import com.cn.car.entity.SysUser;

public interface SysUserDao {
    public List<SysUser> userlist(SysUser user);
    public int userlistcount(SysUser user);
}