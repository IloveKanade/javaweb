package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dbutil.DBUtils;
import com.mvc.idao.INewsDao;
import com.mvc.model.News;


public class NewsDao implements INewsDao {

	@Override
	public News newsDisp(int id) {
		// TODO Auto-generated method stub
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				News news = null;
				String sql = "select * from news where id = ?";
				try {
					conn=DBUtils.getConnection();
					ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					rs=ps.executeQuery();
					if(rs.next()) {
						news = new News();
						news.setId(rs.getInt(1));
						news.setTitle(rs.getString(2));
						news.setContent(rs.getString(3));
						news.setUdate_at(rs.getString(4));
						news.setAdminname(rs.getString(4));
					}
					
				} catch (SQLException e) {
					e.printStackTrace();// TODO: handle exception
				}finally {
					DBUtils.close(conn, ps, rs);
				}
				
				return news;
	}

	@Override
	public int allNewsCont() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int iCount = 0;
		String sql="select count(*) from news";
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				iCount=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, ps, rs);
		}
		return iCount;
	}

	@Override
	public List<News> allNewsList(int page) {
		String sql="select * from news order by id limit ?,? ";
		List<News> list=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		News news=null;
		try{
			list=new ArrayList<News>();
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*Integer.parseInt(DBUtils.SIZE));//从第0条开始
			ps.setInt(2, Integer.parseInt(DBUtils.SIZE));
			rs=ps.executeQuery();
			while(rs.next()){
				news=new News();
				news.setId(rs.getInt(1));
				news.setTitle(rs.getString(2));
				news.setContent(rs.getString(3));
				news.setUdate_at(rs.getString(4));
				news.setAdminname(rs.getString(5));
				list.add(news);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtils.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public boolean newsDelete(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		boolean flag1=false;
		String sql = "delete from news where id=?";
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			flag=ps.executeUpdate();
			if(flag==1) {
				flag1=true;
			}else
				flag1=false;
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		} finally {
			DBUtils.close(conn, ps, null);
		}
		return flag1;
	}

	@Override
	public int newsAdd(News news) {
		Connection conn = null;
		PreparedStatement ps = null;
		int row=0;
		String sql = "insert into news(title,content,update_at,adminname) values(?,?,?,?)";
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, news.getTitle());
			ps.setString(2, news.getContent());
			ps.setString(3, news.getUdate_at());
			ps.setString(4, news.getAdminname());
			row=ps.executeUpdate();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		}catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			DBUtils.close(conn, ps, null);
		}
			return row;
	}

	@Override
	public boolean newsUpdate(News news) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		boolean flag2 =false;
		String sql ="update news set title=?,content=?,update_at=?,adminname=? where id=?";
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, news.getTitle());
			ps.setString(2, news.getContent());
			ps.setString(3, news.getUdate_at());
			ps.setString(4, news.getAdminname());
			ps.setInt(5, news.getId());
			flag=ps.executeUpdate();
			if(flag==1) {
				flag2=true;
			}else
				flag2=false;
			
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		} finally {
			DBUtils.close(conn, ps, null);
		}	
		return flag2;
	}

	@Override
	public News newsDisp(String name, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean newsExists(String title) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag=false;
		String sql = "select * from news where title=?";
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, title);
			rs=ps.executeQuery();
			if(rs.next()) {
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		} finally {
			DBUtils.close(conn, ps, rs);
		}
		return flag;
	}
	public boolean NewsExists2(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag=false;
		String sql = "select * from news where id=?";
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		} finally {
			DBUtils.close(conn, ps, rs);
		}
		return flag;
	
	}

}
