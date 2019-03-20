<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import = "java.util.List" %>
    <%@page import = "com.mvc.model.User" %>
    <%
    	List<User> users = (List<User>)request.getAttribute("list");
    	String bar=request.getAttribute("bar").toString();
    	String count=request.getAttribute("count").toString();
    	String flag=request.getAttribute("flag").toString();
    	Object ousername=session.getAttribute("username");
    	String username=ousername.toString();
    %>
	<jsp:include page="header.jsp"></jsp:include>

  <!-- content start -->
  <div class="admin-content">
    <div class="admin-content-body">
      <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户管理</strong> / <small>User</small></div>
      </div>

      <hr>

      <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
          <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
              <button type="button" class="am-btn am-btn-default" data-am-modal="{target: '#add-user'}"><span class="am-icon-plus" ></span> 新增</button>
              <button type="button" class="am-btn am-btn-default" onclick="dellist()" ><span class="am-icon-trash-o"></span> 删除</button>
            </div>
          </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">
          <div class="am-form-group">
          	<input type="hidden" id="optionselect" value="<%= flag%>">
             <!--<select id="opinlist"  onchange="window.location=this.value" data-am-selected="{btnSize: 'sm'}" >
              <option value="user?flag=0">所有类别</option>
              <option value="user?flag=1">管理员</option>
              <option value="user?flag=2">普通用户</option>
            </select>-->
              <a href="user?flag=0">所有类别</a> | 
            <a href="user?flag=1">管理员</a> | 
            <a href="user?flag=2">普通用户</a>
          </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">
          <div class="am-input-group am-input-group-sm">
            <!--<input type="text" class="am-form-field" id="query-id">-->
            <form action="user" method ="post">
            	<input type="text" id="queryname" name="queryname" />
            	<input type="submit" value="搜索"/>	
            	<%=request.getAttribute("ss") %>>
            </form>
          <span class="am-input-group-btn">
            <!--<button class="am-btn am-btn-default" type="button" onclick="query()" id="a">搜索</button>-->
          </span>
          </div>
        </div>
      </div>
            <input type="hidden" name="nowname" id="nowname"value=<%=username %>>
      <div class="am-g">
        <div class="am-u-sm-12">
          <form class="am-form">
            <table class="am-table am-table-striped am-table-hover table-main">
              <thead>
              <tr>
                <th class="table-check"><input type="checkbox" name="selAll" id="selAll"/></th>
                <th class="table-id">ID</th>
                <th >用户名</th>
                <th class="am-hide-sm-only">类别</th>
                <th class="am-hide-sm-only">修改日期</th><!--am-hide-sm-only  宽屏幕显示 窄屏幕隐藏-->
                <th class="table-set">操作</th>
              </tr>
              </thead>
              <tbody>
              <%for (User u : users) {%>
              <tr>
                <td><input type="checkbox" name="userlist" value="<%=u.getId() %>"/> </td>
                <td><%=u.getId() %></td>
                <td><%=u.getName() %></td>
                <td class="am-hide-sm-only">
       			<%if(u.getType()==1){ %>
                	管理员	<%}else{ %>普通用户
                <%} %>
                </td>
                
                <td class="am-hide-sm-only"><%=u.getUdate_at() %></td>
                <td>
                  <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs"><!--修改密码要传参数 username  id 传到pop层上-->
                    	<!--加隐藏字段 方便传值的时候调用   id   username-->
                    	<input type="hidden" value=<%=username%> name="nowname" id="nowname">
                    	<input type="hidden" value=<%=u.getType() %>>
                    	<input type="hidden" value="<%=u.getId() %>" />
                    	<input type="hidden" value="<%=u.getName() %>" /><!--this 是DOM对象 指的是激发的当前的对象-->
                      <button type="button" class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="edit(this)"><span class="am-icon-pencil-square-o"></span> 修改密码</button>
                      <button type="button" class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="edit2(this)"><span class="am-icon-pencil-square-o"></span> 编辑</button>
                      <button type="button" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="delone(this)" data-id = "<%= u.getId()%>"><span class="am-icon-trash-o"></span> 删除</button>
                    </div>
                  </div>
                </td>
              </tr>
              <%} %>
              </tbody>
            </table>
             <div class="am-cf">
              <div class="am-fr">
              	共<%=count %>条记录
                <ul class="am-pagination">
					<%=bar %>
                </ul>
              </div>
            </div>
            <hr />
          </form>
        </div>

      </div>
    </div>

    <footer class="admin-content-footer">
      <hr>
      <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
    </footer>

  </div>
  <!-- content end -->
</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<!--新增用户弹出层start-->
<div class="am-popup" id="add-user" style="z-index: 1510; height: 500px;">
  <div class="am-popup-inner">
    <div class="am-popup-hd">
      <h4 class="am-popup-title">添加用户</h4>
      <span data-am-modal-close
            class="am-close">&times;</span>
    </div>
    <div class="am-popup-bd">
    	<form class="am-form" id="fm-add-user">
    		<div class="am-form-group">
	    		<label for="username">用户名:</label>
	    		<input type="text" class="admin-form-text" required="required" minlength="3" name="username" id="username" placeholder="请输入用户名，至少三个字符"data-validation-message="用户名不能为空，至少三个字符" >
    		</div>
    		<div class="am-form-group">
    			<label for="password">密码:</label></td>
	    		<input type="password" class="admin-form-text"required="required" minlength="6" name="password" id="password" placeholder="请输入至少六位密码"data-validation-message="密码不能为空，至少六个字符" >
    		</div>
    		<div class="am-form-group">
    			<label for="repassword">密码:</label></td>
	    		<input type="password" class="admin-form-text"required="required" minlength="6" name="repassword" id="repassword" placeholder="请在此输入密码，保证两次密码输入一致"data-validation-message="确认密码不能为空，至少六个字符" >
    		</div>
    		<div class="am-form-group" >
    			<!--<label for="type">用户类型:</label></td>-->
	    		<label><input type="radio"  name="type" value="0"checked="checked">普通用户</label>
	    		<label><input type="radio"  name="type" value="1">管理员</label>
    		</div>
	    		<div class="am-form-group" align="center">
	    			<input type="button" onclick="regisger()" value="确定" class="am-btn am-btn-primary"/> 
	    			<input type="button" onclick="$('#add-user').modal('close')" value="关闭" class="am-btn am-btn-primary"/> 
	    		</div><!--modal(close) 使用amazeui的方法-->
    	</form>
    </div>
  </div>
</div>

<!--新增用户弹出层end-->
<!--修改密码弹出层start-->
<div class="am-popup" id="edit-user" style="z-index: 1510; height: 500px;">
  <div class="am-popup-inner">
    <div class="am-popup-hd">
      <h4 class="am-popup-title">修改密码</h4>
      <span data-am-modal-close
            class="am-close">&times;</span>
    </div>
    <div class="am-popup-bd">
    	<form class="am-form" id="fm-edit-user">
    		<div class="am-form-group">
	    		<label for="username">用户名:</label>
	    		<input type="text" class="admin-form-text" name="username" id="edit-username" readonly="readonly">
    		</div>
    		<div class="am-form-group">
    			<label for="password">密码:</label></td>
	    		<input type="password" class="admin-form-text"required="required" minlength="6" name="password" id="edit-password" placeholder="请输入至少六位密码"data-validation-message="密码不能为空，至少六个字符" >
    		</div>
    		<div class="am-form-group">
    			<label for="repassword">确认密码:</label></td>
	    		<input type="password" class="admin-form-text"required="required" minlength="6" name="repassword" id="edit-repassword" placeholder="请在此输入密码，保证两次密码输入一致"data-validation-message="确认密码不能为空，至少六个字符" >
    		</div>
	    		<div class="am-form-group" align="center">
	    			<!--加隐藏框存ID -->
	    			<input type="hidden" name="edit-type" id="edit-type" >
	    			<input type="hidden" name="edit-id" id="edit-id" />
	    			<input type="button" value="提交" onclick="update(this)" data-id="1" class="am-btn am-btn-primary"/> 
	    			<input type="button" onclick="$('#edit-user').modal('close')" value="关闭" class="am-btn am-btn-primary"/> 
	    		</div><!--modal(close) 使用amazeui的方法-->
    	</form>
    </div>
  </div>
</div>

<!--修改密码弹出层end-->
<div class="am-popup" id="edit2-user" style="z-index: 1510; height: 500px;">
  <div class="am-popup-inner">
    <div class="am-popup-hd">
      <h4 class="am-popup-title">修改密码</h4>
      <span data-am-modal-close
            class="am-close">&times;</span>
    </div>
    <div class="am-popup-bd">
    	<form class="am-form" id="fm-edit2-user">
    		<div class="am-form-group">
	    		<label for="username">用户名:</label>
	    		<input type="hidden" name="husername" id="husername">
	    		<input type="text" class="admin-form-text" name="username2" id="edit2-username" >
    		</div>
    		<div class="am-form-group">
    			<label for="password">密码:</label></td>
	    		<input type="password" class="admin-form-text"required="required" minlength="6" name="password2" id="edit2-password" placeholder="请输入至少六位密码"data-validation-message="密码不能为空，至少六个字符" >
    		</div>
    		<div class="am-form-group">
    			<label for="repassword">确认密码:</label></td>
	    		<input type="password" class="admin-form-text"required="required" minlength="6" name="repassword2" id="edit2-repassword" placeholder="请在此输入密码，保证两次密码输入一致"data-validation-message="确认密码不能为空，至少六个字符" >
    		</div>
	    		<div class="am-form-group" align="center">
	    			<!--加隐藏框存ID 222-->
	    			    		<div class="am-form-group" >
    			<!--<label for="type">用户类型:</label></td>-->
	    		<label><input type="radio"  name="edit2-type" value="0"checked="checked">普通用户</label>
	    		<label><input type="radio"  name="edit2-type" value="1">管理员</label>
    		</div>
	    			<input type="hidden" name="edit2-id" id="edit2-id" />
	    			<input type="button" value="提交" onclick="update2(this)" data-id="2" class="am-btn am-btn-primary"/> 
	    			<input type="button" onclick="$('#edit2-user').modal('close')" value="关闭" class="am-btn am-btn-primary"/> 
	    		</div><!--modal(close) 使用amazeui的方法-->
    	</form>
    </div>
  </div>
</div>
<!--删除弹出层start-->
<div class="am-modal am-modal-confirm" tabindex="-1" id="delete-user">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">删除用户</div>
    <div class="am-modal-bd">
      你，确定要删除<span id="delete-username"></span>用户吗？
    </div>
    <div class="am-modal-footer">
    	<input type="hidden" name="delete-id" id="delete-id" /><!--确定要删除谁  一定要传递ID-->
      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
    </div>
  </div>
</div>
<!--删除弹出层end-->

	<jsp:include page="footer.jsp"></jsp:include>
