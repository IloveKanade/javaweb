<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>用户登录</title>
		<link href="css/style.css" type="text/css">
		<script src="jquery/jquery-1.8.3.min.js" type="text/javascript"></script>  
		<script type="text/javascript" src="jquery/init.js" ></script>
		<style type="text/css">
			body{
				margin: 0px;font-size: 12px;
			}
			.box{
				border: 1px solid #D1DEB2; width: 150px; height: 20px;
			}
			.div1{
				background-image: url(images/bgg.jpg);
				height: 600px;
				width: 803px;
				padding-left:20px;
				padding-top:220px;
				text-align:left;				
			}
		</style>		
	</head>
	<body>
		<div align="center">
			<div class="div1">
				<form action="" method="post" >
					<table align="center" border="0" width="500">
						<tr>
							<td align="right" width="30%" size ="9pt">用户名：</td>
							<td><input type="text" name="username" id="username" class="box"></td>
						</tr>
						<tr>
							<td align="right">密 码：</td>
							<td><input type="password" name="password" id="password" class="box"></td>
						</tr>												
						<tr>
							<td colspan="2" align="center" height="40">
								<input type="button" onclick="login()" value="登录">
								<input type="reset"	 onclick="window.location.href='register.jsp'" value="注册">							
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
