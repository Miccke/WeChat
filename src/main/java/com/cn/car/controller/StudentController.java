package com.cn.car.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.car.entity.Student;
import com.cn.car.service.StudentService;
import com.cn.car.util.JsonResult;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@RequestMapping("selectByFy_")
	@ResponseBody
	public  Map<String,Object> selectByFy(int pageSize,int pageNumber,String name,Integer age){
		/*
		 * {limit: 10, offset: 0, departmentname: "", statu: ""}
		 */
		Map<String, Object> params=new HashMap<String, Object>();
		int a=(pageNumber-1)*pageSize;
		int b=pageSize;
		params.put("a", a);
		params.put("b", b);
		params.put("name", name);
		params.put("age", age);
		return studentService.selectByFy(params);
	}
	
	@RequestMapping(value = "/selectByFy",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject findUserList(Integer limit, Integer offset, String departmentname,
			String age) {
		List<Student> list = studentService.findUserList();
		JSONObject obj = new JSONObject();
		obj.put("pageSize", 1);
		obj.put("rows", list);
		obj.put("total", list.size());
		obj.put("totalPage", 2);
		obj.put("page", 0);
		obj.put("hasPreviousPage", true);
		obj.put("hasNextPage", true);
		obj.put("lastPage", false);
		obj.put("firstPage", false);
		return obj;
	}
}
