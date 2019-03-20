package com.mvc.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dao.UserDao;
import com.mvc.dbutil.DBUtils;
import com.mvc.idao.IUserDao;
import com.mvc.model.User;

/**
 * Servlet implementation class front
 */

public class front extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public front() {
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
		
		List<User> list=null;
		IUserDao dao=null;
		int iCount=0;//总数
		int iSize=Integer.parseInt(DBUtils.SIZE);//每页大小
		int iPage=0;//总页数
		int currpage=1;//当前页
		StringBuffer sb=new StringBuffer();
		
		try{
			dao=new UserDao();
			list=new ArrayList<User>();
			list=dao.normalUserList(currpage);
			iCount=dao.normalUserCount();//总页数
		
			//传入的值用getParameter   设置的值用getAttribute
			if(request.getParameter("page")!=null) 
			{currpage=Integer.parseInt(request.getParameter("page"));}
			//获取数据列表    
			list=dao.normalUserList(currpage);
			request.setAttribute("list", list);

			//获取导航条  
			if(iCount%iSize==0){
				iPage=iCount/iSize;
			}else{
				iPage=iCount/iSize+1;
			}
			for(int i=1;i<=iPage;i++){
				if(i==currpage){
					sb.append(" [ "+i+" ] ");
				}else{
					sb.append("<a href='front?page="+i+"'>"+i+"</a>");
				}
				sb.append(" ");
			}
			request.setAttribute("bar",sb.toString());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			request.getRequestDispatcher("front/frontUserList.jsp").forward(request, response);
			//分发    servlet运行原理 
			//getRequestDispatcher  两个来回  servlet-->servlet在服务器端组建（pin）有js脚本的话，不执行，拼装servlet完成就全部-->客户端，如果有jq，则可以解析
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
