<%@ page contentType="text/html;charset=UTF-8" language="java"
	session="false"
%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
aaaaaaaaaaaaaaaaaaaaaaaaaa
<shiro:hasPermission name="sys:user:*">
	<div>has sys:user:* permission</div>
</shiro:hasPermission>
<shiro:hasRole name="admin" >
	<div>has admin role</div>
</shiro:hasRole>
<div></div>
UserRole:
<shiro:user>admin</shiro:user>
<shiro:guest>guest</shiro:guest>
</body>
</html>
