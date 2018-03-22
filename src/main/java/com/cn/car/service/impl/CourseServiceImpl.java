package com.cn.car.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.car.dao.CourseDao;
import com.cn.car.entity.Course;
import com.cn.car.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseDao courseDao;
	@Override
	public List<Course> list(Course user) {
		return courseDao.list(user);
	}
	@Override
	public int listCount(Course user) {
		return courseDao.listCount(user);
	}


}
