package com.mvc.bll;

import java.io.IOException;
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
 * Servlet implementation class UpdateUserServlet
 */
//@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		IUserDao dao = new UserDao();
		try {
			User user=new User();
			user=dao.userDisp(id);
			request.setAttribute("user",user);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			request.getRequestDispatcher("admin/admUserEdit.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			String name=request.getParameter("username");
			String pwd=request.getParameter("password");
			String type=request.getParameter("utype");
			String fun=request.getParameter("fun");
			IUserDao dao = new UserDao();
			User user = new User();
			boolean flag=false;
			boolean flag2=false;
			if(fun.equals("2")) {
				flag2=dao.userExists(name);
				
			}
			if(flag2)
			{
				json.put("status", 4);
			}
			else {System.out.println(id+"asaaa");
				user.setId(id);
				user.setName(name);
				user.setPwd(pwd);
				user.setType(Integer.parseInt(type));
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				user.setUdate_at(df.format(new Date()));
				flag=dao.userUpdate(user);
				if(flag==true) {
					json.put("status", 1);
				}else if(flag==false) {
					json.put("status", 2);
				}else {
					json.put("status", 3);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		} finally {
			response.getOutputStream().write(json.toString().getBytes("utf-8"));
		}
	}

}
