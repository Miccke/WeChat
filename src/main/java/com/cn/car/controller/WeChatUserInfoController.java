package com.cn.car.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.car.entity.RefreshToken;
import com.cn.car.entity.WeChatUserInfo;
import com.cn.car.util.HttpXmlClient;
import com.cn.car.util.RefreshTokenUtil;
import com.cn.car.util.UrlEncodeUTF8;
import com.google.gson.Gson;

@Controller
@RequestMapping("weChatUser")
public class WeChatUserInfoController {

	static Properties prop = new Properties();
	static {
		InputStream in = WeChatUserInfoController.class.getClassLoader()
				.getResourceAsStream("jdbc.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static final String redirect_uri = "http://www.miccke.top/weChatUser/";
	public Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("/clock")
	public void redirectPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ prop.getProperty("AppID")
				+ "&redirect_uri="
				+ UrlEncodeUTF8.urlEncodeUTF8(redirect_uri + "?method=clock")
				+ "&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
		response.sendRedirect(userURL);
	}

	@RequestMapping("/index")
	@ResponseBody
	public void getIndex(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		String userURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ prop.getProperty("AppID")
				+ "&redirect_uri="
				+ UrlEncodeUTF8.urlEncodeUTF8(redirect_uri + "?method=index")
				+ "&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
		response.sendRedirect(userURL);
	}

	@RequestMapping("/")
	@ResponseBody
	public void getWeChatUserInfo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String method = request.getParameter("method");
		String page = "";
		if (method.equals("clock")) {
			page = "personal.jsp";
		} else if (method.equals("index")) {
			page = "index_.html";
		}
		// 用户同意授权
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			RefreshToken refreshToken = RefreshTokenUtil.getRefreshToken(
					prop.getProperty("AppID"), 
					prop.getProperty("AppSecret"),
					code);
			session.setAttribute("code", code);
			// 网页授权接口访问凭证
			String accessToken = refreshToken.getAccessToken();
			session.setAttribute("accessToken", accessToken);
			// 用户标识
			String openId = refreshToken.getOpenId();
			// 获取用户信息
			WeChatUserInfo weChatUserInfo = RefreshTokenUtil.getWeChatUserInfo(accessToken, openId);

			// 设置要传递的参数
			request.setAttribute("weChatUserInfo", weChatUserInfo);
			request.setAttribute("state", state);
			session.setAttribute("weChatUserInfo", weChatUserInfo);
		}

		// 跳转到page
		request.getRequestDispatcher("../WeChat/" + page).forward(request,response);
	}
	

	 @RequestMapping("/signature")    
	 public void getIndex(HttpServletRequest request,PrintWriter out,HttpSession session){  

	       // ModelAndView mav = new ModelAndView("index");
	        JSONObject object = new  JSONObject();
	      //获取access_token
			Map<String, String> params = new HashMap<String, String>();
			String xml = HttpXmlClient.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx6fc4f8ea2aeb91b4&secret=aae6174472f6bca9d7b1eae7fff33236");
		    System.out.println(xml);

			JSONObject jsonMap  = JSONObject.fromObject(xml);
			Map<String, String> map = new HashMap<String, String>();
		    Iterator<String> it = jsonMap.keys();  
		    while(it.hasNext()) {  
		        String key = (String) it.next();  
		        String u = jsonMap.get(key).toString();
		        map.put(key, u);  
		    }
		    String access_token = map.get("access_token");
		    System.out.println("access_token=" + access_token);
		    
		    //获取ticket
		    params.put("access_token",access_token);
		    params.put("type","jsapi");
		  //  xml = HttpXmlClient.post("https://api.weixin.qq.com/cgi-bin/ticket/getticket",params); 
		    xml = HttpXmlClient.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi"); 
		    jsonMap  = JSONObject.fromObject(xml);
			map = new HashMap<String, String>();
		    it = jsonMap.keys();  
		    while(it.hasNext()) {  
		        String key = (String) it.next();  
		        String u = jsonMap.get(key).toString();
		        map.put(key, u);  
		    }
		    String jsapi_ticket = map.get("ticket");
		    
		    
		    //获取签名signature
		    String noncestr = UUID.randomUUID().toString();
		    String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		    //获取请求url
		    String path = request.getContextPath();
	        String url = request.getScheme() + "://" + request.getServerName() +  path + "/course/getCourse";  
//	        String url = request.getScheme() + "://" + request.getServerName() +  path + "/WeChat/index.jsp";  
		    String str = "jsapi_ticket=" + jsapi_ticket +
	                "&noncestr=" + noncestr +
	                "&timestamp=" + timestamp +
	                "&url=" + url;
		    //sha1加密
		    String signature = HttpXmlClient.SHA1(str);
		    
		    object.put("signature", signature);   
		    object.put("timestamp", timestamp);   
		    object.put("nonceStr", noncestr);   
		    object.put("appId", "wx6fc4f8ea2aeb91b4");  
	        System.out.println("jsapi_ticket=" + jsapi_ticket);
	        System.out.println("nonceStr=" + noncestr);
	        System.out.println("timestamp=" + timestamp);
	        System.out.println("url=" + url);
	        System.out.println("str=" + str);
	        System.out.println("signature=" + signature);
	        
	        Gson gson = new Gson();
	        out.println(gson.toJson(object));
			out.flush();
			out.close();
	    }    

}
