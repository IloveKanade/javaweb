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

import com.mvc.dao.NewsDao;
import com.mvc.idao.INewsDao;
import com.mvc.model.News;

/**
 * Servlet implementation class UpdateNewsServlet
 */
public class UpdateNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNewsServlet() {
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
			System.out.println(request.getParameter("id"));
			int id=Integer.parseInt(request.getParameter("id"));
			String title=request.getParameter("title");
			String adminname=request.getParameter("adminname");
			String content=request.getParameter("content");
			String fun = request.getParameter("fun");
			INewsDao dao = new NewsDao();
			News news = new News();
			boolean flag=false;
			boolean flag2=false;
			if(fun.equals("2")) {
				flag2=dao.newsExists(title);
			}
			if(flag2)
			{
				json.put("status", 4);
			}
			else {
				news.setId(id);
				news.setTitle(title);
				news.setAdminname(adminname);
				news.setContent(content);;
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				news.setUdate_at(df.format(new Date()));
				flag=dao.newsUpdate(news);
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
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
