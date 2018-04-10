package com.cn.car.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cn.car.entity.SysUser;
import com.cn.car.service.SysUserService;
import com.cn.car.util.JsonToMap;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
    private SysUserService sysUserService ;

	@SuppressWarnings("unchecked")
	@RequestMapping("/login")
    public void login(HttpServletRequest request,HttpServletResponse response, SysUser user, PrintWriter out, HttpSession session){
        	try {
        		request.setCharacterEncoding("utf-8");
        		response.setCharacterEncoding("utf-8");
        		JSONObject result = new JSONObject();
				String data = request.getParameter("data").substring(1,request.getParameter("data").length()-1);
				Gson gson = new Gson();
				
				Map<String, Object> map = new HashMap<String,Object>();
				map = gson.fromJson(data, map.getClass());
				String userName = (String) map.get("username");
				String password = (String) map.get("password");
				user.setUserName(userName);
				user.setPassword(password);
				
				int count = sysUserService.loginUser(user);
				if(count == 1 ){
					session.setAttribute("userName", userName);
					session.setAttribute("password", password);
					result.put("success", true);
					result.put("page", "index.jsp");
					out.print(result);
					out.flush();
					out.close();
				}else{
					result.put("error",false);
					result.put("page", "login.jsp");
					out.print(result);
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
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

    @RequestMapping(value = "/checkUser")
    public void checkUser(HttpServletRequest request, HttpServletResponse response,String openId) throws Exception{ 	
    	openId = request.getParameter("openId");
    	SysUser user  = sysUserService.checkUser(openId);
    	Gson gson = new Gson();
    	PrintWriter pw = response.getWriter();
		pw.write(gson.toJson(user)); 
		pw.flush();
		pw.close();
    }  
    @RequestMapping(value = "/blindUser")
    public void blindUser(HttpServletRequest request, HttpServletResponse response,SysUser user) throws Exception{ 	
    	try {
			sysUserService.blindUser(user);
			PrintWriter pw = response.getWriter();
			pw.write("true"); 
			pw.flush();
			pw.close();
		} catch (Exception e) {
			PrintWriter pw = response.getWriter();
			pw.write("false"); 
			pw.flush();
			pw.close();
			e.printStackTrace();
		}
    	
    }  
}
