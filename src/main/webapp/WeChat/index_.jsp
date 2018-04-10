<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />

<title>学生打卡</title>
<script type="text/javascript" src="../WeChat/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="../WeChat/js/jquery-1.11.1.min.js"></script>
<style type="text/css">
		body{
			background: #F3F3F3
		}
		li{
			list-style: none;
			font-size: 12px;
			height: 40px;
			width: 100%
		}
		.top{
			margin-top: 30px;
			height:200px;
		}
		.clock{
			margin-left: -20px; 
			width: 100%;
			line-height: 30px; 
			background: #fff;
			text-align:center
		 }
	</style>	
</head>

<body>
	<div class="top">	
		<ul >
			<li><div class="clock">2018-03-15 16:41:36&nbsp;&nbsp;&nbsp;&nbsp;信息楼&nbsp;&nbsp;&nbsp;&nbsp;张晓毅&nbsp;&nbsp;&nbsp;&nbsp;大学物理</div></li>
			<li><div class="clock">2018-03-15 16:41:36&nbsp;&nbsp;&nbsp;&nbsp;信息楼&nbsp;&nbsp;&nbsp;&nbsp;张晓毅&nbsp;&nbsp;&nbsp;&nbsp;大学物理</div></li>
			<li><div class="clock">2018-03-15 16:41:36&nbsp;&nbsp;&nbsp;&nbsp;信息楼&nbsp;&nbsp;&nbsp;&nbsp;张晓毅&nbsp;&nbsp;&nbsp;&nbsp;大学物理</div></li>
			<li><div class="clock">2018-03-15 16:41:36&nbsp;&nbsp;&nbsp;&nbsp;信息楼&nbsp;&nbsp;&nbsp;&nbsp;张晓毅&nbsp;&nbsp;&nbsp;&nbsp;大学物理</div></li>
			<li><div class="clock">2018-03-15 16:41:36&nbsp;&nbsp;&nbsp;&nbsp;信息楼&nbsp;&nbsp;&nbsp;&nbsp;张晓毅&nbsp;&nbsp;&nbsp;&nbsp;大学物理</div></li>
		</ul>
	</div>
	<p style="font-size: 12px;color: #FF7F00"><span style="margin-left: 40px"><label>课程：</label><span>${course.course}</span></span>   <span style="float: right; margin-right: 40px"><label>教师：</label><span>${course.teacher}</span></span></p>
	<div align="center" style="margin-top: 80px">
		
		<p></p>
		<div style="width: 110px;height: 110px; border-radius: 55px; background:#F2DFC3" onClick = "binding()">
			<div style="width: 100px;height: 100px; border-radius: 50px; background:#FF7F00; margin-left: 5px; margin-top: 5px;float: left" align="center">
				<p align="center" style="margin-top: 40px; color:aliceblue ">点击打卡</p>
			</div>
		</div>
	</div>	
	<div style="color: #FF7F00; font-size: 12px;border:1px dashed  #FF7F00;text-align: center; height: 50px;line-height: 50px;border-radius: 25px;background: #fff;margin-top: 50px; overflow:hidden;"	>
		湖南工学院信息楼大学物理教室<img src="../WeChat/images/reload.png" style="width: 20px;height: 20px;margin-left: 40px;vertical-align:middle;" onClick = "localtion()"/>
	</div>
</body>
</html>

