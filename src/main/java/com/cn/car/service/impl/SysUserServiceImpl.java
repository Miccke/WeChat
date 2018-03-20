package com.cn.car.service.impl;

import org.springframework.stereotype.Service;

import com.cn.car.dao.SysUserDao;
import com.cn.car.entity.SysUser;
import com.cn.car.service.SysUserService;

import javax.annotation.Resource;

import java.util.Set;

/**
 * Created with IDEA
 * Created by ${jie.chen} on 2016/7/14.
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDao sysUserDao ;

    public SysUser findUserByUsername(String username) {
        SysUser SysUser = sysUserDao.findUserByUsername(username);
        return SysUser;
    }

    public Set<String> findRoles(String username) {
        return sysUserDao.findRoles(username);
    }

    public Set<String> findPermissions(String username) {
        return sysUserDao.findPermissions(username);
    }
}
