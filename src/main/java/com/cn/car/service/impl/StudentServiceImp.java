package com.cn.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.car.dao.StudentDao;
import com.cn.car.entity.Student;
import com.cn.car.service.StudentService;
@Service("studentService")
public class StudentServiceImp implements StudentService {
	@Autowired
	private StudentDao studentDao;

	public Map<String,Object> selectByFy(Map<String, Object> param) {
		//bootstrap-table要求服务器返回的json须包含：totlal，rows
		Map<String,Object> result = new HashMap<String,Object>();
		int total=studentDao.selectByFy(null).size();
		List<Student> rows=studentDao.selectByFy(param);
		result.put("total",total);
		result.put("rows",rows);
		return result;
	}

	public List<Student> findUserList() {
		// TODO Auto-generated method stub
		return studentDao.findUserList();
	}
	
	

}