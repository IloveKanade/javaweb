package com.mvc.bll;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mvc.dao.NewsDao;
import com.mvc.dao.UserDao;
import com.mvc.idao.INewsDao;
import com.mvc.idao.IUserDao;

/**
 * Servlet implementation class DeleteNewsServlet
 */
public class DeleteNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNewsServlet() {
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
		int id=Integer.parseInt(request.getParameter("id"));
		try {
			INewsDao dao =new NewsDao();
			flag=dao.newsDelete(id);
			if(flag) {
				json.put("status", 1);
			}else if(flag==false) {
				json.put("status", 2);
			}else {
				json.put("status", 3);
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
