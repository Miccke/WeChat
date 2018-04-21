package com.cn.car.controller;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.car.entity.Course;
import com.cn.car.entity.UserCourse;
import com.cn.car.service.CourseService;
import com.cn.car.service.SysUserService;
import com.cn.car.service.UserCourseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller  
@RequestMapping("/userCourse")

public class UserCourseController {
	@Resource
	private UserCourseService userCourseService;
	@Resource
	private SysUserService userService;
	@Resource
	private CourseService courseService;
	
	@RequestMapping(value = "/list")
	private void menulist(HttpServletRequest request,HttpServletResponse reponse,PrintWriter out,HttpSession session){
		try {
//			WeChatUserInfo weChatUserInfo = (WeChatUserInfo) session.getAttribute("weChatUserInfo"); 
//			String openId = weChatUserInfo.getOpenId();
			String openId = "o-d5C0g2KYZwUarSq2BVRFTZtqo8";
			Long userId = Long.parseLong(userService.checkUser(openId).getId().toString());
			List<UserCourse> list = userCourseService.getList(userId);
			List<Course> colist = new ArrayList<Course>();
			for(UserCourse uc:list){
				Course course = courseService.getById(uc.getId());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
				SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
				
				Date endTime = format.parse(format.format(course.getEndTime()));
				Date startTime  = format.parse(format.format(course.getStartTime()));
				
				Date day  = format2.parse(format.format(course.getStartTime()));
				course.setStartTime(startTime);
				course.setEndTime(endTime);
				course.setDay(day);
				colist.add(course);
			}
			Gson gson = new   GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			out.print(gson.toJson(colist));
			out.flush();
			out.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}  
