package com.cn.car.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cn.car.entity.SysUser;
import com.cn.car.entity.User;
import com.cn.car.service.SysUserService;
import com.cn.car.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Resource
    private SysUserService sysUserService ;
	
	@RequestMapping("/userlist")
	@ResponseBody
	public ModelAndView getUserList(HttpServletRequest request,HttpServletResponse response, User user, PrintWriter out) {
		HttpSession session = request.getSession();
		session.setAttribute("username", user);
		int bool = userService.selectUser(user);
		ModelAndView mv = new ModelAndView();
		String page = "signin";
		if(bool == 1){
			page = "index";
			mv.setViewName(page);
		}
		return mv;
	}
	


    @RequestMapping("/loginAdmin")
    public String login(SysUser user, Model model){
        Subject subject = SecurityUtils.getSubject() ;
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword()) ;
        try {
            subject.login(token);
            return "index" ;
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

}
