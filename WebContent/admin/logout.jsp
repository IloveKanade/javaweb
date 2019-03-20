<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	session.setAttribute("username", null);
	session.setAttribute("user-id","0");
	session.setAttribute("user-type","");
	out.println("<script>alert('退出登录');location.href='login.jsp'</script>");
%>