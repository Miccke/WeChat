package com.cn.car.service;

import java.util.List;

import com.cn.car.entity.Course;

public interface CourseService {
	public List<Course> list(Course user);
	public int listCount(Course user);
	public Course getCourse(Course user);
	public Course getById(Long id);
}

