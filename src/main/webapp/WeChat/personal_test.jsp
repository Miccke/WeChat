<%@page import="com.cn.car.entity.WeChatUserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/WeChat/";

	 // 获取由OAuthServlet中传入的参数
    WeChatUserInfo user = (WeChatUserInfo)request.getAttribute("weChatUserInfo"); 
   // String state=request.getAttribute("state").toString();
    if(null != user) {
%>
    
<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />

<link href="../../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />

<link href="http://at.alicdn.com/t/font_6yxmrwgmg7kl0udi.css" rel="stylesheet" type="text/css" />
<title>我的</title>

</head>

<body style="background-color:#f3f3f3">
<% 
       
    %> 
<!--头部-->
<div class="header_gls" style="background:#FF7F00"> 
<div style="height:2.7em;"></div>
	<a href="./personal.jsp">
     	<img src="<%=user.getHeadImgUrl()%>" style="border-radius:2.5em; width:5em; height:5em;">
        <p color:#000;font-size:16px;><%=user.getNickname()%></p>
    </a>
</div>

<!--列表-->	
<div style="margin-top:20px;">
		<div style="width:90%;margin-left:5%;background-color:#FFF; height:4em;  padding-left:1em; padding-right:1em; padding-top:0.8em; border:1px solid #FF7F00; border-radius:0.5em">
			<a href="#">
				<p class="biao"><img src="images/my_dinner_icon.png" style="width:1.5em; margin-right:1em;line-height:4em; "/>打卡记录<i class="iconfont icon-jiantou"></i></p>
			</a>
		</div>
        <div style="width:90%;margin-left:5%;background-color:#FFF; height:4em;  padding-left:1em; padding-right:1em; padding-top:0.8em; border:1px solid #FF7F00; border-radius:0.5em;margin-top:0.5em">
			<a href="#">
				<p class="biao"><img src="images/my_server_icon.png" style="width:1.5em; margin-right:1em"/>课程查询<i class="iconfont icon-jiantou"></i></p>
			</a>
		</div>
</div>

<!--退出登录-->
<div  style="width:100%; text-align:center" >
	<div class="one" style="width:90%;height: 3.5em;line-height: 3.5em;border-radius: 1.75em;margin:7em 0 0 5%; background:#FF7F00">  
			<p onclick="WeixinJSBridge.call('closeWindow');" class="exit" style="font-size:1.15em; font-family:'微软雅黑'; line-height:3.5em;color:#fff">退出登陆</p>
		
	</div>
</div>
<%
        }
        else 
            out.print("用户不同意授权,未获取到用户信息！");
%>
<script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>

<script src="../pc/js/bootstrap.min.js" type="text/javascript"></script>
	
</body>
</html>
