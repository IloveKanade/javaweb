<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.mvc.model.User"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改</title>
<script type="text/javascript" src="jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="jquery/init.js"></script>
</head>
<body>
 <% User user = (User)request.getAttribute("user"); %>
 <form action="UpdateUserServlet" method="post" onsubmit="return userCheck()">
  <table align="center" width="300" border="1" height="180" cellspacing="0"> 
  <tr bgcolor="white">
		<td align="center" colspan="2">
			<h2>序号为<%=user.getId()%>的用户信息</h2>
		</td>
	</tr>
  <tr align="center">
    <td>
     <b>ID</b>
    </td>
    <td>
     <input name="id" id="id" type="text" readonly="readonly" value="<%=user.getId()%>">
    </td>
  </tr>
  <tr align="center">
    <td>
     <b>用户名</b>
    </td>
    <td>
    <input name="username" id="username" type="text" value="<%=user.getName()%>">
    </td>
  </tr>
  <tr align="center">
    <td>
     <b>密码</b>
    </td>
    <td>
     <input name="password" id="password" type="password" value="<%=user.getPwd()%>">
    </td>
  </tr>
  <tr align="center">
    <td>
     <b>类型</b>
    </td>
    <td>
     <input name="utype" id="utype" type="radio" value="1" <%=(user.getType()==1?"checked":"")%>>管理员&nbsp;
     <input name="utype" id="utype" type="radio" value="0" <%=(user.getType()==0?"checked":"")%>>普通用户
    </td>
  </tr>
  
  <tr align="center">
    <td colspan="2">
     <input type="submit"  name="UpdateUserServlet" value="编辑"/>    
     <input type="reset"  name="reset" value="重置"/>
    </td>
   </tr>	
  </table>
 </form>
</body>
</html>