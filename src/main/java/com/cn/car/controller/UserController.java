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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.car.entity.SysUser;
import com.cn.car.service.SysUserService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
    private SysUserService sysUserService ;

    @RequestMapping("/loginAdmin")
    public String login(HttpServletRequest request,HttpServletResponse response, SysUser user, Model model){
        try {
            return "../../back/manager/main" ;
        }catch (Exception e){
            //这里将异常打印关闭是因为如果登录失败的话会自动抛异常
//            e.printStackTrace();
            model.addAttribute("error","用户名或密码错误") ;
            return "../../login" ;
        }
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/student")
    public String student(){
        return "admin" ;
    }

    @RequestMapping("/teacher")
    public String teacher(){
        return "admin" ;
    }
    @RequestMapping("/userlist")
    public void SearchEmployees(HttpServletRequest request, HttpServletResponse response,SysUser user) throws Exception{ 		
        //查询条件
        String realName = request.getParameter("key");
        if(StringUtils.isNotEmpty(realName)){
        	user.setRealName(realName);
        }
        //分页
        int currentPage = Integer.parseInt(request.getParameter("pageIndex"));
        
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));        
        
        user.setCurrentPage(currentPage);
        user.setPageSize(pageSize);
        //字段排序
        
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        
        if(StringUtils.isNotEmpty(sortField)){
        	user.setSortField(sortField);
        }
        if(StringUtils.isNotEmpty(sortOrder)){
        	user.setSortOrder(sortOrder);
        }
        
        List<SysUser> list = sysUserService.userlist(user);
        Gson gson = new Gson();
        int total = sysUserService.userlistcount(user); 

        Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("data", list);

		PrintWriter pw = response.getWriter();
		pw.write(gson.toJson(map)); 
		pw.flush();
		pw.close();

    }

}
