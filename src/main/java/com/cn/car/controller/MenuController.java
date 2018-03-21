package com.cn.car.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.car.entity.Menu;
import com.cn.car.service.MenuService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {
	
	@Resource
	private MenuService menuService;
	
	@RequestMapping(value = "/list")
	private void menulist(HttpServletRequest request,HttpServletResponse reponse,PrintWriter out){
		List<Menu> list = menuService.menulist();
		Gson gson = new  Gson();
		out.print(gson.toJson(list));
		System.out.println(gson.toJson(list));
		out.flush();
		out.close();
	}
	
}
