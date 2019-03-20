package com.mvc.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mvc.dao.UserDao;
import com.mvc.dbutil.DBUtils;
import com.mvc.idao.IUserDao;
import com.mvc.model.User;


/**
 * Servlet implementation class main
 */

public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//因为是通过URL跳转过来的，所以默认get方法
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		String flag="0";
		int queryid=0;
		int queryflag=0;
		List<User> list=null;
		IUserDao dao=null;
		User QueryUser=null;
		StringBuffer ss = new StringBuffer();
		int iCount=0;//总数
		int iSize=Integer.parseInt(DBUtils.SIZE);//每页大小
		int iPage=0;//总页数
		int currpage=1;//当前页
		if(request.getParameter("queryname")!=null) {
			dao=new UserDao();
			if(!request.getParameter("queryname").equals("")) {
				queryid=Integer.parseInt(request.getParameter("queryname"));
			}
			if(dao.userExists2(queryid)) {
				queryflag=1;
				QueryUser=dao.userDisp(queryid);
			}else if(request.getParameter("queryname").equals("")){
				ss.append("<script>alert('搜索为空')</script>");
			}else {
				ss.append("<script>alert('你查找的用户不存在')</script>");
			}
		}
		if(request.getParameter("flag")!=null) {
			flag=request.getParameter("flag");
		}
		try{
			if(request.getParameter("page")!=null) {
				currpage=Integer.parseInt(request.getParameter("page"));
			}
			if(flag.equals("0")) {
				dao=new UserDao();
				list=new ArrayList<User>();
				list=dao.allUserList(currpage);
				iCount=dao.allUserCont();//总页数				
			} else if(flag.equals("1")) {
				//管理员
				dao=new UserDao();
				list=new ArrayList<User>();
				list=dao.adminUserList(currpage);
				iCount=dao.adminUserCount();//总页数
			} else {
				dao=new UserDao();
				list=new ArrayList<User>();
				list=dao.normalUserList(currpage);
				iCount=dao.normalUserCount();//总页数
			}
			
			//传入的值用getParameter   设置的值用getAttribute
			if(queryflag==1) {
				list.clear();
				list.add(QueryUser);
			}
			//获取数据列表    
			request.setAttribute("list", list);
			request.setAttribute("ss", ss);
			//获取导航条  
			if(iCount%iSize==0){
				iPage=iCount/iSize;
			}else{
				iPage=iCount/iSize+1;
			}
			StringBuffer sp = new StringBuffer();
			if(queryflag==1) {
				iCount=1;
				iPage=1;
			}
			if(currpage==1)                                  
				sp.append(" <li class='am-disabled'><a href='#'>«</a></li>");
			else{
				sp.append("<li ><a href='user?page="+(currpage-1)+"&flag="+flag+"'>«</a></li>");
			}
			for(int i=1;i<=iPage;i++){
				if(i==currpage){
					sp.append("<li class='am-active'>"+i+"</li> ");
				}else{
					sp.append("<li><a href='user?page="+i+"&flag="+flag+"'>"+i+"</a></li>");
				}
			}
			if(currpage==iPage)
				sp.append("<li class='am-disabled'><a href='#'>»</a></li>");
			else
				sp.append("<li><a href='user?page="+(currpage+1)+"&flag="+flag+"'>»</a></li>");
			request.setAttribute("bar",sp.toString());
			request.setAttribute("count", iCount);
			request.setAttribute("flag", flag);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//response.getOutputStream().write(json.toString().getBytes("utf-8"));\
			request.getRequestDispatcher("admin-user.jsp").forward(request, response);
			}			
			//分发    servlet运行原理 
			//getRequestDispatcher  两个来回  servlet-->servlet在服务器端组建（pin）有js脚本的话，不执行，拼装servlet完成就全部-->客户端，如果有jq，则可以解析
			
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}