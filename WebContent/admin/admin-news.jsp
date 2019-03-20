<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 在加载页面时 要判断身份 session中的类型全部是Object类型的-->
    <%
    	Object ousername=session.getAttribute("username");
    if(ousername==null){
    	//在页面写入script代码
    	out.println("<script>alert('请先登录');location.href='login.jsp'</script>");
    	return ;//遇到该问题，则不再向下执行
    }
    String type=session.getAttribute("user-type").toString();
    if(!type.equals("1")){
    	out.println("<script>alert('普通用户无权访问后台，请重新登录');location.href='login.jsp'</script>");
    	return ;
    }
    String username=ousername.toString();
    %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>后台管理-新闻管理</title>
  <meta name="description" content="新闻管理">
  <meta name="keywords" content="news">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="assets/css/admin.css">
  <script type="text/javascript" src="assets/js/admin-news.js" ></script>
  <script src="assets/js/amazeui.min.js"></script>
  <script src="assets/js/jquery.min.js"></script>
  <script src="assets/js/amazeui.min.js"></script>
  <script src="assets/js/app.js"></script>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar am-topbar-inverse admin-header">
  <div class="am-topbar-brand">
    <strong>明日科技</strong> <small>新闻管理</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span> <%=username %>管理员 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="logout.jsp"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
      <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
</header>
<%@ page import="java.util.List" %>
	<%@ page import="com.mvc.model.News" %>
	<%
		List<News> news=(List<News>)request.getAttribute("list");
		System.out.print(news.size());
	%>
<div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
      <ul class="am-list admin-sidebar-list">
        <li><a href="admin-index.jsp"><span class="am-icon-home"></span> 首页</a></li>
        <li><a href="user"><span class="am-icon-user"></span> 用户管理</a></li>
        <li><a href="news"><span class="am-icon-newspaper-o"></span> 新闻管理</a></li>
        <li><a href="logout.jsp"><span class="am-icon-sign-out"></span> 注销</a></li>
      </ul>

      <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-bookmark"></span> 公告</p>
          <p>欢迎<span><%=username %></span>进入明日科技新闻管理</p>
        </div>
      </div>

      <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-tag"></span> wiki</p>
          <p>Welcome!<span><%=username %></span></p>
        </div>
      </div>
    </div>
  </div>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">
    <div class="admin-content-body">
      <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">新闻管理</strong> / <small>News</small></div>
      </div>

      <hr>

      <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
          <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
              <button type="button" class="am-btn am-btn-default" data-am-modal="{target: '#add-news'}"><span class="am-icon-plus" ></span> 新增</button>
              <button type="button" class="am-btn am-btn-default" onclick="dellist()"><span class="am-icon-trash-o"></span> 删除</button>
            </div>
          </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">
          <div class="am-input-group am-input-group-sm">
           <form action="news" method ="post">
            	<input type="text" id="queryname" name="queryname" />
            	<input type="submit" value="搜索"/>	
            	<%=request.getAttribute("ss") %>>
            </form>
          </form>
          </div>
        </div>
      </div>

      <div class="am-g">
        <div class="am-u-sm-12">
          <form class="am-form">
            <table class="am-table am-table-striped am-table-hover table-main">
              <thead>
              <tr>
                <th class="table-check"><input type="checkbox" name="selAll" id="selAll"/></th>
                <th class="table-id">ID</th>
                <th >标题</th>
                <th class="am-hide-sm-only">作者</th>
                <th class="am-hide-sm-only">修改日期</th><!--am-hide-sm-only  宽屏幕显示 窄屏幕隐藏-->
                <th class="table-set">操作</th>
              </tr>
              </thead>
              <tbody>
              <%for(News n:news) {%>
              <tr>
                <td><input type="checkbox" name="newslist" value="<%=n.getId()%>"/></td>
                <td><%=n.getId() %></td>
                <td><%=n.getTitle() %></td>
                <td class="am-hide-sm-only"><%=n.getAdminname()%></td>
                <td class="am-hide-sm-only"><%=n.getUdate_at() %></td>
                <td>
                  <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs"><!--修改密码要传参数 username  id 传到pop层上-->
                    	<!--加隐藏字段 方便传值的时候调用   id   username-->
                    	<input type="hidden" name = "edit-id"  value="<%=n.getId()%>"/>
                    	<input type="hidden" name = "edit-adminname"  value="<%=n.getAdminname()%>"/>
                    	<input name="deletename"  type="hidden" value="<%=n.getTitle() %>" /><!--this 是DOM对象 指的是激发的当前的对象-->
                    	<input type="hidden"  name = "edit-content"  value="<%=n.getContent()%>"/>
                      <button type="button" class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="edit1(this)"><span class="am-icon-pencil-square-o"></span> 修改新闻</button>
                      <button type="button" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="delone1(this)" value = "<%=n.getId()%>" name="deleteid" id="deleteid"><span class="am-icon-trash-o"></span> 删除</button>
                    </div>
                  </div>
                </td>
              </tr>
              <%} %>
              </tbody>
            </table>
             <div class="am-cf">
              共<%=request.getAttribute("count") %>条记录
              <div class="am-fr">
                <ul class="am-pagination">
                  <%=request.getAttribute("bar") %>
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

<footer>
  <hr>
  <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
</footer>
<!--新增用户弹出层start-->
<div class="am-popup" id="add-news" style="z-index: 1510; height: 500px;">
  <div class="am-popup-inner">
    <div class="am-popup-hd">
      <h4 class="am-popup-title">新增新闻</h4>
      <span data-am-modal-close
            class="am-close">&times;</span>
    </div>
    <div class="am-popup-bd">
    	<form class="am-form" id="fm-add-news">
    		<div class="am-form-group">
	    		<label for="new-head">新闻标题:</label>
	    		<input type="text" class="admin-form-text"required="required" minlength="3" name="news-head" id="news-head" placeholder="请输入新闻标题"data-validation-message="新闻标题至少要有3个字符" >
	    		
    		</div>
    		<div class="am-form-group">
	    		<label for="new-adminname">新闻作者:</label>
	    		<input type="text" class="admin-form-text"required="required" minlength="3" name="new-adminname" id="new-adminname" placeholder="请输入作者"data-validation-message="新闻作者至少要有2个字符" >
	    		
    		</div>
    		<div class="am-form-group">
    			<label for="new-body">新闻内容:</label></td>
    			<textarea  class="admin-form-text" name="news-body" required="required" id="news-body" minlength="3" placeholder="请输入新闻内容 " data-validation-message="新闻内容至少要有3个字符" style="height: 200px;"></textarea>
    		</div>
	    		<div class="am-form-group" align="center">
	    		<input type="hidden" id="newsid" value="<%=username%>">
	    			<input type="button" value="确定" onclick="addnews()" class="am-btn am-btn-primary"/> 
	    			<input type="button" onclick="$('#add-news').modal('close')" value="关闭" class="am-btn am-btn-primary"/> 
	    		</div><!--modal(close) 使用amazeui的方法-->
    	</form>
    </div>
  </div>
</div>

<!--新增用户弹出层end-->
<!--修改密码弹出层start-->
<div class="am-popup" id="edit-news" style="z-index: 1510; height: 500px;">
  <div class="am-popup-inner">
    <div class="am-popup-hd">
      <h4 class="am-popup-title">修改新闻</h4>
      <span data-am-modal-close
            class="am-close">&times;</span>
    </div>
    <div class="am-popup-bd">
    	<form class="am-form" id="fm-edit-news">
    		<div class="am-form-group">
	    		<label for="edit-head">新闻标题:</label>
	    		<input type="hidden" name="hiddenname" id="hiddenname">
	    		<input type="text" class="admin-form-text"required="required" minlength="3" name="edit-head" id="edit-head" placeholder="请输入新闻标题"data-validation-message="新闻标题至少要有3个字符" >
    		</div>
    		<div class="am-form-group">
	    		<label for="edit-head">新闻作者:</label>
	    		<input type="text" class="admin-form-text"required="required" minlength="2" name="edit1-adminname" id="edit1-adminname" placeholder="请输入新闻作者"data-validation-message="新闻标题至少要有2个字符" >
    		</div>
    		<div class="am-form-group">
    			<label for="new-body">新闻内容:</label></td>
    			<textarea style="height: 200px;" class="admin-form-text" name="edit-body" id="edit-body" minlength="3"  placeholder="请输入新闻内容 " data-validation-message="新闻内容至少要有3个字符" >
	    		</textarea>
    		</div>
	    		<div class="am-form-group" align="center">
	    			<!--加隐藏框存ID -->
	    			<input type="hidden" name="edit1-id" id="edit1-id" />
	    			<input type="button" value="提交" onclick="editnews()" class="am-btn am-btn-primary"/> 
	    			<input type="button" onclick="$('#edit-news').modal('close')" value="关闭" class="am-btn am-btn-primary"/> 
	    		</div><!--modal(close) 使用amazeui的方法-->
    	</form>
    </div>
  </div>
</div>

<!--修改密码弹出层end-->
<!--删除弹出层start-->
<div class="am-modal am-modal-confirm" tabindex="-1" id="delete-news">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">删除新闻</div>
    <div class="am-modal-bd">
    <input type="hidden" value="<%=username %>" id="nowuser">
      你，确定要删除标题为<span id="delete-this-new"></span>的这条新闻吗？
    </div>
    <div class="am-modal-footer">
    	<input type="hidden" name="delete-id" id="delete-id" /><!--确定要删除谁  一定要传递ID-->
      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
    </div>
  </div>
</div>
<!--删除弹出层end-->


<!--<![endif]-->


</body>
</html>
