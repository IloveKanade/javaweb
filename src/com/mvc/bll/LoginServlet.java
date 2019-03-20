package com.mvc.bll;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.mvc.dao.UserDao;
import com.mvc.idao.IUserDao;
import com.mvc.model.User;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");		
       try{
			// 实例化ProductDao					
			String name=request.getParameter("username");
			String password=request.getParameter("password");
			
			IUserDao dao = new UserDao();	
			User user = null;
			//User user=dao.userDisp(name, password);	
			boolean flag = dao.userExists(name);
			if(flag) {
				 user=dao.userDisp(name, password);	
				 if(user!=null) {
					 HttpSession session= request.getSession();
					 session.setAttribute("user-id",user.getId());
					 session.setAttribute("username", user.getName());
					 session.setAttribute("user-type", user.getType());
					 if(user.getType()==1) {
						 json.put("status", 1);//登录后台成功
					 }else if(user.getType()==0){
						 json.put("status", 2);//登录前台
					 }
				 }else {
					 json.put("status", 3);//密码错误
				 }
			}else{
				json.put("status", 4);//用户不存在
			}

			
		}catch(Exception e){
				e.printStackTrace();
		       	//throw new SQLException("查询所有数据失败");
		}finally {
			response.getOutputStream().write(json.toString().getBytes("utf-8"));
		}
	}

}
