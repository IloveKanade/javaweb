<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.mvc.model.User"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单个用户信息</title>
<style type="text/css">
	td{font-size: 12px;}
	h2{margin: 0px}
</style>
</head>
<body>
<%
User user = (User)request.getAttribute("user");
%>
<table align="center" width="450" border="1" height="180" bordercolor="white" bgcolor="black" cellpadding="1" cellspacing="1">
	<tr bgcolor="white">
		<td align="center" colspan="5">
			<h2>单个用户信息</h2>
		</td>
	</tr>
	<tr align="center" bgcolor="#e1ffc1" >
		<td><b>ID</b></td>
		<td><%=user.getId()%></td>				
	</tr>
	<tr align="center" bgcolor="#e1ffc1" >
		<td><b>用户名</b></td>
		<td><%=user.getName()%></td>				
	</tr>
	<tr align="center" bgcolor="#e1ffc1" >
		<td><b>密码</b></td>
		<td><%=user.getPwd()%></td>				
	</tr>
</table>
</body>
</html>