<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="com.mvc.model.User"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<style type="text/css">
	td{font-size: 12px;}
	h2{margin: 0px}
</style>
</head>
<body>
<table align="center" width="450" border="1" height="180" bordercolor="white" bgcolor="black" cellpadding="1" cellspacing="1">
	<tr bgcolor="white">
		<td align="center" colspan="5">
			<h2>所有用户信息</h2>
		</td>
	</tr>
	<tr align="center" bgcolor="#e1ffc1" >
		<td><b>ID</b></td>
		<td><b>用户名称</b></td>
		<td><b>用户密码</b></td>
		<td><b>查    看</b></td>		
	</tr>
	<%
		List<User> list = (List<User>)request.getAttribute("list");
		for(User u : list){
	%>
	<tr align="center" bgcolor="white">
		<td><%=u.getId()%></td>
		<td><%=u.getName()%></td>
		<td><%=u.getPwd()%></td>
		<td>
			<a href="find?id=<%=u.getId()%>">查看</a>
		</td>		
	</tr>
	<%	
		}
	%>
	<tr>
		<td align="center" colspan="5" bgcolor="white">
			<%=request.getAttribute("bar")%>
		</td>
	</tr>
</table>
</body>
</html>