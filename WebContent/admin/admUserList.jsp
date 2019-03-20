<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="com.mvc.model.User"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<script src="jquery/jquery-1.8.3.min.js" type="text/javascript"></script>  
<script type="text/javascript" src="jquery/init.js"></script>
<style type="text/css">
	td{font-size: 12px;}
	h2{margin: 0px}
</style>
</head>
<body>
<table align="center" width="600" border="1" height="300" bordercolor="white" bgcolor="black" cellpadding="1" cellspacing="1">
	<tr bgcolor="white">
		<td align="center" colspan="6">
			<h2>所有用户信息</h2>
		</td>
	</tr>
	<tr align="center" bgcolor="#e1ffc1" >
		<td><b>ID</b></td>
		<td><b>用户名称</b></td>
		<td><b>用户密码</b></td>
		<td><b>用户类型</b></td>
		<td><b>查    看</b></td>
		<td><b>更    新</b></td>					
	</tr>
	<%
		List<User> list = (List<User>)request.getAttribute("list");
		for(User u : list){
	%>
	<tr align="center" bgcolor="white">
		<td><%=u.getId()%></td>
		<td><%=u.getName()%></td>
		<td><%=u.getPwd()%></td>
		<td><%=u.getType() %></td>
		<td>
			<a href="FindUserServlet?id=<%=u.getId()%>">查看</a>
		</td>
		<td>
		  <a href="DeleteUserServlet?id=<%=u.getId()%>">删除</a>		
		  <a href="UpdateUserServlet?id=<%=u.getId()%>">修改</a>		 
		</td>				
	</tr>
	<%	
		}
	%>
	<tr>
		<td align="center" colspan="6" bgcolor="white">
			<%=request.getAttribute("bar")%>
		</td>
	</tr>
	<tr bgcolor="white">
	<td colspan="6">
	<button onclick="window.location.href='admin/admUserAdd.jsp'">添加</button>
	</td>
	</tr>
</table>
</body>
</html>