package com.mvc.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dao.NewsDao;
import com.mvc.dbutil.DBUtils;
import com.mvc.idao.INewsDao;
import com.mvc.model.News;

/**
 * Servlet implementation class newsmain
 */
public class newsmain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newsmain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String flag="0";
		int queryid=0;
		int queryflag=0;
		List<News> list=null;
		INewsDao dao=null;
		News Querynews=null;
		int iCount=0;//总数
		int iSize=Integer.parseInt(DBUtils.SIZE);//每页大小
		int iPage=0;//总页数
		int currpage=1;//当前页
		StringBuffer ss =new StringBuffer();
		if(request.getParameter("queryname")!=null) {
			dao=new NewsDao();
			if(!request.getParameter("queryname").equals("")) {
				queryid=Integer.parseInt(request.getParameter("queryname"));
			}
			if(dao.NewsExists2(queryid)) {
				queryflag=1;
				Querynews=dao.newsDisp(queryid);
			}else if(request.getParameter("queryname").equals("")){
				ss.append("<script>alert('搜索为空')</script>");
			}else {
				ss.append("<script>alert('你查找的新闻不存在')</script>");
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
				dao=new NewsDao();
				list=new ArrayList<News>();
				list=dao.allNewsList(currpage);
				iCount=dao.allNewsCont();//总页数				
			}
			
			//传入的值用getParameter   设置的值用getAttribute
			if(queryflag==1) {
				list.clear();
				list.add(Querynews);
				System.out.println(Querynews.getId());
			}
			//获取数据列表    
			System.out.println(list.size());
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
				sp.append("<li ><a href='news?page="+(currpage-1)+"&flag="+flag+"'>«</a></li>");
			}
			for(int i=1;i<=iPage;i++){
				if(i==currpage){
					sp.append("<li class='am-active'>"+i+"</li> ");
				}else{
					sp.append("<li><a href='news?page="+i+"&flag="+flag+"'>"+i+"</a></li>");
				}
			}
			if(currpage==iPage)
				sp.append("<li class='am-disabled'><a href='#'>»</a></li>");
			else
				sp.append("<li><a href='news?page="+(currpage+1)+"&flag="+flag+"'>»</a></li>");
			request.setAttribute("bar",sp.toString());
			request.setAttribute("count", iCount);
			request.setAttribute("flag", flag);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//response.getOutputStream().write(json.toString().getBytes("utf-8"));\
			request.getRequestDispatcher("admin-news.jsp").forward(request, response);
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
