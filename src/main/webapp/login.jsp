<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"
         session="false"
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
	<meta charset="UTF-8" />
	<title>login jsp</title>
</head>
<body>
<form action="/login">
	<input type="hidden" name="username" value="admin">
	<input type="hidden" name="password" value="admin">
	<button id="login_btn">登录</button>
</form>

</body>
</html>