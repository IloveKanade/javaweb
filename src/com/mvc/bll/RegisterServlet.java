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
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		try {
			String name = request.getParameter("username");
			String password = request.getParameter("password");
			int type=Integer.parseInt(request.getParameter("type"));
			IUserDao dao = new UserDao();System.out.println(name);
			boolean flag=dao.userExists(name);
			if(flag) {
				json.put("status", 1);
			}else {
				User user = new User();
				user.setName(name);
				user.setPwd(password);
				user.setType(type);
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				user.setUdate_at(df.format(new Date()));
				int row=dao.userAdd(user);System.out.println(row);
				if(row==1) {
					json.put("status", 2);
				}else{
					json.put("status", 3);//Êý¾Ý´íÎó
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
				response.getOutputStream().write(json.toString().getBytes("utf-8"));

		}
	}
	
}


	
