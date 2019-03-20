package com.mvc.bll;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Servlet;
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
import com.mvc.model.News;
import com.mvc.model.User;

/**
 * Servlet implementation class AddNewsServlet
 */
public class AddNewsServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		try {
			String title = request.getParameter("title");
			String adminname = request.getParameter("adminname");
			String content=request.getParameter("content");
			NewsDao dao = new NewsDao();
			boolean flag=dao.newsExists(title);
			if(flag) {
				json.put("status", 1);
			}else {
				News news = new News();
				news.setTitle(title);
				news.setAdminname(adminname);
				news.setContent(content);
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				news.setUdate_at(df.format(new Date()));
				int row=dao.newsAdd(news);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
