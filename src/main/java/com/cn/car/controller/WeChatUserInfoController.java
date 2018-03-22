package com.cn.car.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.car.entity.RefreshToken;
import com.cn.car.entity.WeChatUserInfo;
import com.cn.car.util.RefreshTokenUtil;
import com.cn.car.util.UrlEncodeUTF8;

@Controller
@RequestMapping("weChatUser")
public class WeChatUserInfoController {
	
	static Properties prop = new Properties();
	static {
        InputStream in = WeChatUserInfoController.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static final String redirect_uri = "http://www.miccke.top/weChatUser/";
	public Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("/clock")
	public void redirectPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String userURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+prop.getProperty("AppID")+"&redirect_uri="+UrlEncodeUTF8.urlEncodeUTF8(redirect_uri+"?method=clock")+"&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
		response.sendRedirect(userURL);
	}
	
	@RequestMapping("/index")
	@ResponseBody
	public void getIndex(HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		String userURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+prop.getProperty("AppID")+"&redirect_uri="+UrlEncodeUTF8.urlEncodeUTF8(redirect_uri+"?method=index")+"&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
		response.sendRedirect(userURL);
	}
	
	@RequestMapping("/")
	@ResponseBody
	public void getWeChatUserInfo(HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String method = request.getParameter("method");
		String page = "";
		if(method.equals("clock")){
			page = "personal.jsp";
		}else if(method.equals("index")){
			page = "index.jsp";
		}
		// 用户同意授权
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			RefreshToken refreshToken = RefreshTokenUtil.getRefreshToken(prop.getProperty("AppID"), prop.getProperty("AppSecret"),code);
			// 网页授权接口访问凭证
			String accessToken = refreshToken.getAccessToken();
			// 用户标识
			String openId = refreshToken.getOpenId();
			// 获取用户信息
			WeChatUserInfo weChatUserInfo = RefreshTokenUtil.getWeChatUserInfo(
					accessToken, openId);

			// 设置要传递的参数
			request.setAttribute("weChatUserInfo", weChatUserInfo);
			request.setAttribute("state", state);
			session.setAttribute("weChatUserInfo", weChatUserInfo);
		}

		// 跳转到page
		request.getRequestDispatcher("../WeChat/"+page).forward(request, response);
	}
	
}
