<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<base href="${pageContext.request.contextPath }/">
<meta charset="utf-8">
<title>主界面</title>
<link href="back/css/demo.css" rel="stylesheet" type="text/css" />
<script src="back/js/boot.js" type="text/javascript"></script>
<style type="text/css">
body {
	width: 100%;
	height: 100%;
	margin: 0;
	overflow: hidden;
}
</style>

</head>
<body>
	<div id="loginWindow" class="mini-window" title="用户登录"
		style="width: 350px; height: 165px;" showModal="true"
		showCloseButton="false">

		<div id="loginForm" style="padding: 15px; padding-top: 10px;">
			<table>
				<tr>
					<td style="width: 60px;"><label for="username$text">帐号：</label></td>
					<td><input id="username" name="username" class="mini-textbox"
						required="true" style="width: 150px;" /></td>
				</tr>
				<tr>
					<td style="width: 60px;"><label for="pwd$text">密码：</label></td>
					<td><input id="password" name="password"
						onvalidation="onPwdValidation" class="mini-password"
						requiredErrorText="密码不能为空" required="true" style="width: 150px;"
						onenter="onLoginClick" /> &nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td></td>
					<td style="padding-top: 5px;"><a onclick="onLoginClick"
						class="mini-button" style="width: 60px;">登录</a> <a
						onclick="onResetClick" class="mini-button" style="width: 60px;">重置</a>
					</td>
				</tr>
			</table>
		</div>

	</div>





	<script type="text/javascript">
        mini.parse();
        var loginWindow = mini.get("loginWindow");
        loginWindow.show();
		function onLoginClick(e) {
			var form = new mini.Form("#loginWindow");
			form.validate();
			if (form.isValid() == false)
				return;
			var data = form.getData();
			form.validate();
			if (form.isValid() == false)
				return;

			var json = mini.encode([ data ]);
			$.ajax({
				url : "${pageContext.request.contextPath }/user/loginAdmin",
				type : 'post',
				data : {
					data : json
				},
				cache : false,
				success : function(text) {
					loginWindow.hide();
					mini.loading("登录成功，马上转到系统...", "登录成功");
					setTimeout(function() {
						window.location = '${pageContext.request.contextPath }/back/manager/main.jsp';
					}, 1500);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					form.clear();
					mini.loading("登录失败", "登录失败");
				}
			});
		}

		function onResetClick(e) {
			var form = new mini.Form("#loginWindow");
			form.clear();
		}

		function onPwdValidation(e) {
			if (e.isValid) {
				if (e.value.length < 5) {
					e.errorText = "密码不能少于5个字符";
					e.isValid = false;
				}
			}
		}
	</script>

</body>
</html>