<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	System.out.println(basePath);
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<base href="<%=basePath%>">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="pc/js/jquery-1.11.3.min.js"></script>
<body>
	<button onClick = "test();">测试</button>
</body>
<script type="text/javascript">
	function test(){
		$.get("user/userlist", function(data) {
		}, "json");
	}
</script>
</html>
