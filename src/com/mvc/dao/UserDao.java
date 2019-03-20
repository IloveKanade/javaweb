package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dbutil.DBUtils;
import com.mvc.idao.IUserDao;
import com.mvc.model.User;
import com.mysql.jdbc.integration.jboss.ExtendedMysqlExceptionSorter;

public class UserDao implements IUserDao {

	@Override
	public int normalUserCount() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int iCount = 0;
		String sql="select count(*) from user where type = 0";
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
	public int adminUserCount() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int iCount = 0;
		String sql="select count(*) from user where type = 1";
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
	public List<User> normalUserList(int page) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		User user = null;
		List<User> users = null;
		int Size=Integer.parseInt(DBUtils.SIZE);
		String sql = "select * from user where type = 0 order by id limit ?,?";
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*Size);
			ps.setInt(2, Size);
			rs=ps.executeQuery();
			users = new ArrayList<>();
			while(rs.next())
			{
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPwd(rs.getString(3));
				user.setType(rs.getInt(4));
				user.setUdate_at(rs.getString(5));
				users.add(user);
				
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtils.close(conn, ps, rs);
		}System.out.println(users.size()+"普通用户列表");
		return users;
	}
	public List<User> adminUserList(int page) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		User user = null;
		List<User> users = null;
		int Size=Integer.parseInt(DBUtils.SIZE);
		String sql = "select * from user where type = 1 order by id limit ?,?";
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*Size);
			ps.setInt(2, Size);
			rs=ps.executeQuery();
			users = new ArrayList<>();
			while(rs.next())
			{
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPwd(rs.getString(3));
				user.setType(rs.getInt(4));
				user.setUdate_at(rs.getString(5));
				users.add(user);
				
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtils.close(conn, ps, rs);
		}
		return users;
	}

	@Override
	public User userDisp(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		String sql = "select * from user where id = ?";
		try {
			conn=DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPwd(rs.getString(3));
				user.setType(rs.getInt(4));
				user.setUdate_at(rs.getString(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		
		return user;
	}

	@Override
	public int allUserCont() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int iCount = 0;
		String sql="select count(*) from user";
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
	public List<User> allUserList(int page) {
		// TODO Auto-generated method stub
		String sql="select * from user order by id limit ?,? ";
		List<User> list=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=null;
		try{
			list=new ArrayList<User>();
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*Integer.parseInt(DBUtils.SIZE));//从第0条开始
			ps.setInt(2, Integer.parseInt(DBUtils.SIZE));
			rs=ps.executeQuery();
			while(rs.next()){
				user=new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPwd(rs.getString(3));
				user.setType(rs.getInt(4));
				user.setUdate_at(rs.getString(5));
				list.add(user);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtils.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public boolean userDelete(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		boolean flag1=false;
		String sql = "delete from user where id=?";
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
	public int userAdd(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		int row=0;
		String sql = "insert into user(name,psd,type,update_at) values(?,?,?,?)";
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPwd());
			ps.setInt(3, user.getType());
			ps.setString(4, user.getUdate_at());
			row=ps.executeUpdate();System.out.println(row);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
		}catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			DBUtils.close(conn, ps, null);
		}
			return row;
	}

	@Override
	public boolean userUpdate(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		boolean flag2 =false;
		String sql ="update user set name=?,psd=?,type=?,update_at=? where id=?";
		try {System.out.println("zzzzz");
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPwd());
			ps.setInt(3, user.getType());
			ps.setString(4, user.getUdate_at());
			ps.setInt(5, user.getId());
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
	public User userDisp(String name, String pwd) {
		Connection conn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user where name=? and psd=?";
		User user =null;
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			rs=ps.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(name);
				user.setPwd(pwd);
				user.setType(rs.getInt(4));
				user.setUdate_at(rs.getString(5));
			}
		}catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		return user;
	}

	@Override
	public boolean userExists(String name) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag=false;
		String sql = "select * from user where name=?";
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
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
	public boolean userExists2(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag=false;
		String sql = "select * from user where id=?";
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
