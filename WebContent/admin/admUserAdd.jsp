<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加</title>
<script type="text/javascript" src="jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="jquery/init.js"></script>
</head>
<body>
 <form action="" method="post" onsubmit="return userCheck()">
  <table align="center" width="300" border="1" height="180" cellspacing="0"> 
  <tr bgcolor="white">
		<td align="center" colspan="2">
			<h2>新增用户信息</h2>
		</td>
	</tr>  
  <tr align="center">
    <td>
     <b>用户名</b>
    </td>
    <td>
    <input name="username" id="username" type="text" value="">
    </td>
  </tr>
  <tr align="center">
    <td>
     <b>密码</b>
    </td>
    <td>
     <input name="password" id="password" type="password" value="">
    </td>
  </tr>
  <tr align="center">
    <td>
     <b>类型</b>
    </td>
    <td>
     <input name="utype" id="utype" type="radio" value="1">管理员&nbsp;
     <input name="utype" id="utype" type="radio" value="0" >普通用户
    </td>
  </tr>
  
  <tr align="center">
    <td colspan="2">
     <input type="button" onclick="add()" value="添加"> 
     <input type="reset"  name="reset" value="重置"/>
    </td>
   </tr>	
  </table>
 </form>
</body>
</html>