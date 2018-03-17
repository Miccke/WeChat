package com.cn.car.service;

import java.util.List;
import java.util.Map;

import com.cn.car.entity.Student;

public interface StudentService {
	Map<String,Object> selectByFy(Map<String,Object> param);
	public List<Student> findUserList();
}
