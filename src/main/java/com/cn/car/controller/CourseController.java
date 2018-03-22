package com.cn.car.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.car.entity.Course;
import com.cn.car.service.CourseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Resource
    private CourseService courseService ;

    @RequestMapping("/list")
    public void SearchEmployees(HttpServletRequest request, HttpServletResponse response,Course course) throws Exception{ 		
        //查询条件
        String key = request.getParameter("key");
        if(StringUtils.isNotEmpty(key)){
        	course.setCourse(key);
        }
        //分页
        int currentPage = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));        
        
        course.setCurrentPage(currentPage);
        course.setPageSize(pageSize);
        
        //字段排序
        
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        
        if(StringUtils.isNotEmpty(sortField)){
        	course.setSortField(sortField);
        }
        if(StringUtils.isNotEmpty(sortOrder)){
        	course.setSortOrder(sortOrder);
        }
        
        List<Course> list = courseService.list(course);
        for(int i = 0; i < list.size(); i++){
        	System.out.println(list.get(i).getStartTime());
        }
        // gson 格式化时间格式
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); 
        int total = courseService.listCount(course); 

        Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("data", list);
		
		PrintWriter pw = response.getWriter();
		pw.write(gson.toJson(map)); 
		pw.flush();
		pw.close();

    }

}
