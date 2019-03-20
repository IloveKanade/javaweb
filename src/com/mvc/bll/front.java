package com.mvc.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dao.UserDao;
import com.mvc.dbutil.DBUtils;
import com.mvc.idao.IUserDao;
import com.mvc.model.User;

/**
 * Servlet implementation class front
 */

public class front extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public front() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��Ϊ��ͨ��URL��ת�����ģ�����Ĭ��get����
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		
		List<User> list=null;
		IUserDao dao=null;
		int iCount=0;//����
		int iSize=Integer.parseInt(DBUtils.SIZE);//ÿҳ��С
		int iPage=0;//��ҳ��
		int currpage=1;//��ǰҳ
		StringBuffer sb=new StringBuffer();
		
		try{
			dao=new UserDao();
			list=new ArrayList<User>();
			list=dao.normalUserList(currpage);
			iCount=dao.normalUserCount();//��ҳ��
		
			//�����ֵ��getParameter   ���õ�ֵ��getAttribute
			if(request.getParameter("page")!=null) 
			{currpage=Integer.parseInt(request.getParameter("page"));}
			//��ȡ�����б�    
			list=dao.normalUserList(currpage);
			request.setAttribute("list", list);

			//��ȡ������  
			if(iCount%iSize==0){
				iPage=iCount/iSize;
			}else{
				iPage=iCount/iSize+1;
			}
			for(int i=1;i<=iPage;i++){
				if(i==currpage){
					sb.append(" [ "+i+" ] ");
				}else{
					sb.append("<a href='front?page="+i+"'>"+i+"</a>");
				}
				sb.append(" ");
			}
			request.setAttribute("bar",sb.toString());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			request.getRequestDispatcher("front/frontUserList.jsp").forward(request, response);
			//�ַ�    servlet����ԭ�� 
			//getRequestDispatcher  ��������  servlet-->servlet�ڷ��������齨��pin����js�ű��Ļ�����ִ�У�ƴװservlet��ɾ�ȫ��-->�ͻ��ˣ������jq������Խ���
			
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
