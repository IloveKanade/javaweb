package com.mvc.idao;

import java.util.List;

import com.mvc.model.User;

public interface IUserDao {
	public int normalUserCount();
	public List<User>normalUserList(int page);
	public User userDisp(int id);
	public int allUserCont();
	public List<User> allUserList(int page);
	public boolean userDelete(int id);
	public int userAdd(User user);
	public boolean userUpdate(User user);
	public User userDisp(String name,String pwd);		//find user by name and pwd
	public boolean userExists(String name);
	public int adminUserCount();
	public List<User> adminUserList(int page);
	public boolean userExists2(int queryid);
	
}
