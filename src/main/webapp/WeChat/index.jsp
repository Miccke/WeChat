<%@page import="com.cn.car.entity.WeChatUserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	 // 获取由OAuthServlet中传入的参数
   	WeChatUserInfo user = (WeChatUserInfo)session.getAttribute("weChatUserInfo"); 
   	String openId = user.getOpenId();
  //  String openId = "Miccke123456";
    
%>
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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=8qCA9IG7tah98yjZMAsUQQgF5x9aFIBM"></script>
<script type="text/javascript">
	function binding() {
		$.post("../user/checkUser",{openId:'<%=openId%>'},function(data){
			if(data){
				var local = localtion();
			}else{
				var userName = prompt("请输入您的学号进行绑定：", "");
				$.post("../user/blindUser",{openId:'<%=openId%>',userName:userName},function(data){
					if(data){
						alert("绑定成功");				
					}else{
						alert("绑定失败请联系管理员");
					}
				},"json")
			}
		},"json")
	}
	
	
	
	function localtion() {
		var longLat = new Object();
		function temp() {
			var value = '';
			$.ajax({
				url : "../weChatUser/signature",
				async : false,
				dataType : 'json',
				success : function(data) {
					value = data;
				}
			});
			return value;
		};
		var obj = temp();

		//注入配置config接口
		wx.config({
			debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId : obj.appId, // 必填，企业号的唯一标识，此处填写企业号corpid
			timestamp : obj.timestamp, // 必填，生成签名的时间戳
			nonceStr : obj.nonceStr, // 必填，生成签名的随机串
			signature : obj.signature,// 必填，签名，见附录1
			jsApiList : [ 'checkJsApi', 'getLocation' ]
		// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});

		wx.ready(function() {
			wx.getLocation({
				success : function(res) {
					var longitude = res.longitude; // 纬度，浮点数，范围为90 ~ -90
					var latitude = res.latitude; // 经度，浮点数，范围为180 ~ -180。
					var speed = res.speed; // 速度，以米/每秒计
					var accuracy = res.accuracy; // 位置精度
					longLat.longitude = longitude;
					longLat.latitude = latitude;
					
					// 创建地理编码实例      
					var address = "";
					var myGeo = new BMap.Geocoder();      
					// 根据坐标得到地址描述    
					myGeo.getLocation(new BMap.Point(longitude, latitude), function(result){      
						debugger;
					    if (result){      
					    	alert(result.address);   
					    	address = result.address;
					    	var id = ${course.id};
					    	$.get("../cr/address",{longitude:longitude,latitude:latitude,address:address,id:id},function(data){
								if(data == 1){
					    			alert("打卡成功");
								}else{
					    			alert("打卡失败");
								}
							},"json");
					    }      
					});
				},
				fail : function(res) {
					alert('请确认您的手机已经允许微信使用GPS！！！')
				}
			});
		});
	}
</script>

