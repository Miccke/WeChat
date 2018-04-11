package com.cn.car.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.car.entity.UserCourse;



public interface UserCourseDao {
	/**
	 * 学生课程列表
	 * @return
	 */
	public List<UserCourse> getList(@Param("user_id") Long user_id);
}