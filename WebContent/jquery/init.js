function regisger(){
	var name = $("#username").val();
	if(name==""){
		alert("用户名不能为空");
		$("#username").focus();
		return ;
	}
	var pwd=$("#password").val();
	if(pwd==""){
		alert("密码不能为空");
		$("#password").focus();
		return ;
	}
	var pwd2=$("#password2").val();
	if(pwd!=pwd2){
		alert("密码不一致");
		$("#password").focus()
		return ;
	}
	var type=2;
	var types=document.getElementsByName("utype"); 
	for(var i=0;i<types.length;i++){
		if(types[i].checked){
			type=types[i].value;
			break;
		}
	}
	if(type!="1"&&type!="0"){
		alert("请选择用户类型");
		return ;
	}
	$.ajax({
		data:{"username":name,"password":pwd,"utype":type},
		dataType:"json",
		type:"post",
		url:"add-user",
		success:function(data){
			if(data.status==1){
				alert("用户名已存在");
			}else if(data.status==2){
				alert("注册成功");
				window.location.href="index.jsp";
			}else if(data.status==3){
				alert("注册失败");
			}else{
				alert("未知错误");
			}
		}
	});
	}
	function login(){
		var name=$("#username").val();
		var name = $("#username").val();
		if(name==""){
			alert("用户名不能为空");
			$("#username").focus();
			return ;
		}
		var pwd=$("#password").val();
		if(pwd==""){
			alert("密码不能为空");
			$("#password").focus();
			return ;
		}
		$.ajax({
			data:{"username":name,"password":pwd},
			dataType:"json",
			type:"post",
			url:"login",
			success:function(data){
				if(data.status==1){
					alert("欢迎登录，即将登录后台");
					location.href="main";
				}else if(data.status==2){
					alert("普通用户无权访问后台")
				}else if(data.status==3){
					alert("密码错误");
				}else if(data.status==4){
					alert("用户不存在");
				}else{
					alert("未知错误");
				}
			}
		});
	}
	function add(){
		var name = $("#username").val();
		if(name==""){
			alert("用户名不能为空");
			$("#username").focus();
			return ;
		}
		var pwd=$("#password").val();
		if(pwd==""){
			alert("密码不能为空");
			$("#password").focus();
			return ;
		}
		var pwd2=$("#password2").val();
		if(pwd!=pwd2){
			alert("密码不一致");
			$("#password").focus()
			return ;
		}
		var type=2;
		var types=document.getElementsByName("utype"); 
		for(var i=0;i<types.length;i++){
			if(types[i].checked){
				type=types[i].value;
				break;
			}
		}
		if(type!="1"&&type!="0"){
			alert("请选择用户类型");
			return ;
		}
		$.ajax({
			data:{"username":name,"password":pwd,"utype":type},
			dataType:"json",
			type:"post",
			url:"http://localhost:8080/MyMVC1208/AddUserServlet",
			success:function(data){
				if(data.status==1){
					alert("用户名已存在");
				}else if(data.status==2){
					alert("添加成功");
					window.location.href="index.jsp";
				}else if(data.status==3){
					alert("添加失败");
				}else{
					alert("未知错误");
				}
			}
		});
		}
