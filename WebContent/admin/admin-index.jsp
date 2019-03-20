<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <!-- 在加载页面时 要判断身份 session中的类型全部是Object类型的-->
   <jsp:include page="header.jsp"></jsp:include>
   
  <!-- content start -->
  <div class="admin-content">
    <div class="admin-content-body">
      <div class="am-cf am-padding">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> / <small>一些常用模块</small></div>
      </div>

      <ul class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
        <li><a href="news" class="am-text-default"><span class="am-icon-btn am-icon-newspaper-o"></span><br/>新闻管理</a></li>
         <li><a href="user" class="am-text-default"><span class="am-icon-btn am-icon-user-md"></span><br/>用户管理</a></li>
      </ul>

      


    </div>
<jsp:include page="footer.jsp"></jsp:include>
    