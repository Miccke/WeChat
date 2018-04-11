package com.cn.car.service;

import java.util.List;

import com.cn.car.entity.UserCourse;


public interface UserCourseService {
	/**
	 * 学生课程列表
	 * @return
	 */
	public List<UserCourse> getList(Long userId);
}
