<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/pc/";
%>
<!DOCTYPE html>
<html class="login-bg">
<base href="<%=basePath%>">
<head>
	<title>Detail Admin - Sign in</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />

    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/lib/font-awesome.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/signin.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
   <div class="row-fluid login-wrapper">
        <a href="index.html">
            <img class="logo" src="img/logo-white.png" />
        </a>

        <div class="span4 box">
            <div class="content-wrap">
            	<form action="../user/userlist">
            		<h6>Log in</h6>
	                <input class="username" name="username" type="text" placeholder="Your username" />
	                <input class="password" name="password" type="password" placeholder="Your password" />
	                <a href="#" class="forgot">Forgot password?</a>
	                <div class="remember">
	                    <input id="remember-me" type="checkbox" />
	                    <label for="remember-me">Remember me</label>
	                </div>
	                
	                <input class="btn-glow primary login" type="submit" onclick="login();" value = "Log in"/>
            	</form>
                
<!--                 <a class="btn-glow primary login" href="javascript:void(0)" onclick="login();">Log in</a> -->
            </div>
        </div>

        <div class="span4 no-account">
            <p>Don't have an account?</p>
            <a href="signup.html">Sign up</a>
        </div>
    </div>

	<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>
	
	<script type="text/javascript">
		function login(){
			var  username = $.trim($('.username').val());
			var  password = $.trim($('.password').val());
			$.get('../user/userlist',{username:username,password:password},function(data){
				if(data){
					location.href = data;
				}
			
			},'json')
		}
	</script>

</body>
</html>