package com.mvc.bll;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dao.NewsDao;
import com.mvc.dbutil.DBUtils;
import com.mvc.model.News;

/**
 * Servlet implementation class NewsListServlet
 */
public class NewsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				int iCurrPage=1;//�赱ǰҳ����ʼֵΪ1
				List<News> newss=null;
				NewsDao dao=new NewsDao();
				int iCount=0;//������
				int iPages=0;//��ҳ��
				int iSize=Integer.parseInt(DBUtils.SIZE);//ÿҳ����
				try {
					if(request.getParameter("page")!=null) {
						iCurrPage=Integer.parseInt(request.getParameter("page"));//��ȡ�ڼ�ҳ������
					}
					newss=dao.allNewsList(iCurrPage);
					System.out.println(newss);
					request.setAttribute("list", newss);
					iCount=dao.allNewsCont();
					if(iCount%iSize==0) {
						iPages=iCount/iSize;
					}else {
						iPages=(iCount/iSize)+1;
					}
					request.setAttribute("count", iCount);
					request.setAttribute("pages", iPages);
					request.setAttribute("curpage", iCurrPage);
					request.setAttribute("isize", iSize);
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					request.getRequestDispatcher("newslist.jsp").forward(request, response);
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
