package com.mvc.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtils {
	public static String URL;
	public static String USERNAME;
	public static String PASSWORD;
	public static String SIZE;
	public static String DRIVER;
	private static ResourceBundle rb=ResourceBundle.getBundle("com.mvc.dbutil.db-config");
	public DBUtils(){
		super();
	}
	static {
		URL=rb.getString("url");
		USERNAME=rb.getString("username");
		PASSWORD=rb.getString("password");
		SIZE=rb.getString("size");
		DRIVER=rb.getString("driver");
	
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// TODO: handle exception
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public static Connection getConnection() {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
		return conn;
	}
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs) {
		try {
			if(conn!=null) {
				conn.close();
			}
			if(ps!=null) {			
				ps.close();
			}
			if(rs!=null) {
				rs.close();
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
