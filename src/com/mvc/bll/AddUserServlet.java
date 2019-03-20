package com.mvc.bll;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mvc.dao.UserDao;
import com.mvc.idao.IUserDao;
import com.mvc.model.User;

/**
 * Servlet implementation class AddUserServiet
 */
//@WebServlet("/AddUserServiet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		request.getRequestDispatcher("admin/admUserAdd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try {
			String name=request.getParameter("username");
			String pwd=request.getParameter("password");
			String type=request.getParameter("utype");
			IUserDao dao = new UserDao();
			boolean flag=dao.userExists(name);
			if(flag) {
				json.put("status", 1);
			}else {
				User user = new User();
				user.setName(name);
				user.setPwd(pwd);
				user.setType(Integer.parseInt(type));
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				user.setUdate_at(df.format(new Date()));
				int row = dao.userAdd(user);
				if(row==1) {
					json.put("status", 2);
				}else{
					json.put("status", 3);//Êý¾Ý´íÎó
				}
			}
			
		}  catch (Exception e) {
			// TODO: handle exception
		}finally {
			response.getOutputStream().write(json.toString().getBytes("utf-8"));
		}
	}

}
