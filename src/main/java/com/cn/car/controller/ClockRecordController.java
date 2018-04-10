package com.cn.car.controller;

import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.car.entity.ClockRecord;
import com.cn.car.entity.WeChatUserInfo;
import com.cn.car.service.ClockRecordService;

@Controller
@RequestMapping("/cr")
public class ClockRecordController {

	@Resource
    private ClockRecordService clockRecordService ;

	 
	 @RequestMapping("/address")
	 public void getAddress(HttpServletRequest request,PrintWriter out,HttpSession session){
		 try {
			 request.setCharacterEncoding("utf-8");
			 String longitude = request.getParameter("longitude");//纬度 
			 String latitude = request.getParameter("latitude");//经度 
			 String address = request.getParameter("address");//地址
			 String id = request.getParameter("id");//ID 
			 WeChatUserInfo user = (WeChatUserInfo) session.getAttribute("weChatUserInfo");
			 String openId = user.getOpenId();			 
			 System.out.println(longitude+"  "+latitude+"  "+address);
			 ClockRecord cr = new ClockRecord();
			 cr.setAddress(address);
			 cr.setCo_id(Integer.parseInt(id));
			 cr.setDate(new Date());
			 cr.setLatitude(latitude);
			 cr.setLongitude(longitude);
			 cr.setOpenId(openId);
			 ClockRecord cr1 = clockRecordService.getCr(cr);
			 if(cr1 != null){
				 cr1.setAddress(address);
				 cr1.setCo_id(Integer.parseInt(id));
				 cr1.setDate(new Date());
				 cr1.setLatitude(latitude);
				 cr1.setLongitude(longitude);
				 cr1.setOpenId(openId);
				 clockRecordService.updateCr(cr);
			 }else{
				 clockRecordService.insert(cr);
			 }
			 
			 out.print(1);
			 out.flush();
			 out.close();
		} catch (Exception e) {
			e.printStackTrace();
			out.print(0);
			out.flush();
			out.close();
		}
	 }
}
