package com.cn.car.dao;

import java.util.Set;

import com.cn.car.entity.SysUser;

public interface SysUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

	SysUser findUserByUsername(String username);
	
	Set<String> findRoles(String username);

    Set<String> findPermissions(String username);

}