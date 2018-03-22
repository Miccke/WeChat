package com.cn.car.dao;

import java.util.List;

import com.cn.car.entity.Course;

public interface CourseDao {
	public List<Course> list(Course user);
	public int listCount(Course user);
}