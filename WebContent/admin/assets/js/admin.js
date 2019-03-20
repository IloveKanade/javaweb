//后台专用
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
	var pwd2=$("#repassword").val();
	if(pwd!=pwd2){
		alert("密码不一致");
		$("#repassword").focus()
		return ;
	}
	var type=2;
	var types=document.getElementsByName("type"); 
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
		data:{"username":name,"password":pwd,"type":type},
		dataType:"json",
		type:"post",
		url:"add-user",
		success:function(data){
			if(data.status==1){
				alert("用户名已存在");
			}else if(data.status==2){
				alert("添加用户成功");
				$("#add-user").modal("close");
				location.href="user";
			}else if(data.status==3){
				alert("添加失败");
			}else{
				alert("未知错误");
			}
		}
	});
	}//login开始
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
					location.href="admin-index.jsp";
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

$(function() {
  $('#fm-add-user').validator({
    onValid: function(validity) {
      $(validity.field).closest('.am-form-group').find('.am-alert').hide();
    },

    onInValid: function(validity) {
      var $field = $(validity.field);
      var $group = $field.closest('.am-form-group');
      var $alert = $group.find('.am-alert');
      // 使用自定义的提示信息 或 插件内置的提示信息
      var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

      if (!$alert.length) {
        $alert = $('<div class="am-alert am-alert-danger"></div>').hide().
          appendTo($group);
      }
      $alert.html(msg).show();
    }
  });
});

$(function() {
  $('#fm-edit-user').validator({
    onValid: function(validity) {
      $(validity.field).closest('.am-form-group').find('.am-alert').hide();
    },

    onInValid: function(validity) {
      var $field = $(validity.field);
      var $group = $field.closest('.am-form-group');
      var $alert = $group.find('.am-alert');
      // 使用自定义的提示信息 或 插件内置的提示信息
      var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

      if (!$alert.length) {
        $alert = $('<div class="am-alert am-alert-danger"></div>').hide().
          appendTo($group);
      }
      $alert.html(msg).show();
    }
  });
});
	
function edit(obj){
	var username=$(obj).prev().val();//将DOM对象转化为JQUERY对象
	var id=$(obj).prev().prev().val();
	var type=$(obj).prev().prev().prev().val();
	$("#edit-username").val(username);
	$("#edit-id").val(id);
	$("#edit-type").val(type);
	$("#edit-user").modal("open");
}
function edit2(obj){
	var username=$(obj).prev().prev().val();//将DOM对象转化为JQUERY对象
	var id=$(obj).prev().prev().prev().val();
	var type=$(obj).prev().prev().prev().prev().val();
	$("#edit2-username").val(username);
	$("#edit2-id").val(id);
	$("#husername").val(username);
	$("#edit2-user").modal("open");
}
function delone(obj){
	var username=$(obj).prev().prev().prev().val();
	var nowname=$("#nowname").val();
	if(username==nowname){
		alert("无法删除当前登录用户");
		return ;
	}
	alert(nowname);
	var id=$(obj).data('id');
		$("#delete-username").html(username);//span标签
		$("#delete-id").val(id);//input 标签
		$("#delete-user").modal(
			{
				onConfirm:function(){//为取消绑定事件
					//ajax
					$.ajax({
						data:{"id":id},
						dataType:"json",
						type:"post",
						url:"delete-user",
						success:function(data){
							
							if(data.status==1){
								alert("确认删除id为"+id+"的用户");
								location.href="user";
							}else if(data.status==2){
								alert("删除失败");
							}else {
								alert("未知错误");
							}
						}
					});
				},
				onCancel:function(){
					alert("放弃删除");
				}
			}
		);//设置弹出框显示  modal可以传递参数 json
}
function update(obj){
	var id = $("#edit-id").val();
	var username = $("#edit-username").val();
	var password =  $("#edit-password").val();
	var repassword = $("#edit-repassword").val()
	var type = $("#edit-type").val();
	var fun=$(obj).data('id');
	if(password==""){
		alert("密码不能为空");
		$("#password").focus();
		return ;
	}
	if(password!=repassword){
		alert("密码不一致");
		$("#repassword").focus()
		return ;
	}
	$.ajax({
		data:{"id":id,"username":username,"password":password,"utype":type,"fun":fun},
		dataType:"json",
		type:"post",
		url:"edit-user",
		success:function(data){
			if(data.status==1){
				alert("修改成功");
				location.href="user";
			}else if(data.status==2){
				alert("修改失败")
				return ;
			}else if(data.status==3){
				alert("未知错误");
				return ;
			}else {
				alert("用户已存在");
				return;
			}
		}
	});
}
function update2(obj){
	var id = $("#edit2-id").val();
	var username = $("#edit2-username").val();
	var husername = $("#husername").val();
	var password =  $("#edit2-password").val();
	var repassword = $("#edit2-repassword").val()
	var type=2;
	var types=document.getElementsByName("edit2-type"); 
	for(var i=0;i<types.length;i++){
		if(types[i].checked){
			type=types[i].value;
			break;
		}
	}
	var fun=$(obj).data('id');
	if(password==""){
		alert("密码不能为空");
		$("#password").focus();
		return ;
	}
	if(password!=repassword){
		alert("密码不一致");
		$("#repassword").focus()
		return ;
	}
	if(username==husername){
		fun="1";
	}
	$.ajax({
		data:{"id":id,"username":username,"password":password,"utype":type,"fun":fun},
		dataType:"json",
		type:"post",
		url:"edit-user",
		success:function(data){
			if(data.status==1){
				alert("修改成功");
				location.href="user";
			}else if(data.status==2){
				alert("修改失败")
				return ;
			}else if(data.status==3){
				alert("未知错误");
				return ;
			}else {
				alert("用户已存在");
				return;
			}
		}
	});
}
function query(){
	var id=$("#query-id").val();
	if(id=""){
		alert("搜索id不能为空");
	}
	$("#b").val(id);
}
/*$("#checkAll").click(function() {alert("@@@");
	var rows = $("#content").find('input');
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].type == "checkbox") {
			var e = rows[i];
			e.checked = this.checked;
		}
	}
});*/
window.onload=function(){
    selAll=document.getElementById("selAll");
    hobbys =document.getElementsByName("userlist");

    //全选事件
    selAll.onclick=function(){
        //全选
        if(selAll.checked==true){
            for(var i=0;i<hobbys.length;i++){
                hobbys[i].checked=true;
            }
        }else{
            for(var i=0;i<hobbys.length;i++){
                hobbys[i].checked=false;
        }
    }
}
}
function dellist(){
	var cks=document.getElementsByName("userlist");
	var nowname=$("#nowname").val();
	if(!confirm("确定要删除这些用户吗？")){
		 return ;
		 }
	
	for(var i=0;i<cks.length;i++){
		 if(cks[i].checked){
		 //alert(cks[i].value);
			 $.ajax({
					data:{"id":cks[i].value,"nowname":nowname},
					dataType:"json",
					type:"post",
					url:"delete-user",
					success:function(data){
						
						if(data.status==1){
							alert("删除成功");
						}else if(data.status==2){
							alert("删除失败");
						}else if(data.status==3){
							alert("未知错误");
						}else{
							alert("无法删除当前登录"+nowname+"用户");
						}
					}
				});
			 }
		 }
	location.href="user";
}
