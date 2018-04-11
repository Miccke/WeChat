package com.cn.car.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.car.dao.UserCourseDao;
import com.cn.car.entity.UserCourse;
import com.cn.car.service.UserCourseService;

@Service("userCourseService")
public class UserCourseServiceImpl implements UserCourseService {

	@Resource
	private UserCourseDao userCourseDao;
	/**
	 * 学生课程列表
	 * @return
	 */
	public List<UserCourse> getList(Long userId){
		return userCourseDao.getList(userId);
	}

}
