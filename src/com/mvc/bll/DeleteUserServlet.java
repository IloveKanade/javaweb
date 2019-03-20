package com.mvc.bll;

import java.io.IOException;
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
 * Servlet implementation class DeleteUserServlet
 */
//@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		boolean flag = false;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		User user = new User();
		int id=Integer.parseInt(request.getParameter("id"));
		String nowname=request.getParameter("nowname");
		try {
			IUserDao dao =new UserDao();
			user=dao.userDisp(id);
			System.out.println(user.getName()+"  "+nowname);
			if(!user.getName().equals(nowname)) {
				flag=dao.userDelete(id);
				if(flag) {
					json.put("status", 1);
				}else if(flag==false) {
					json.put("status", 2);
				}else {
					json.put("status", 3);
				}
			}else {
				json.put("status", 4);
			}
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			response.getOutputStream().write(json.toString().getBytes("utf-8"));
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
