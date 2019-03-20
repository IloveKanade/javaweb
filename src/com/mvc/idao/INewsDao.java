package com.mvc.idao;

import java.util.List;

import com.mvc.model.News;

public interface INewsDao {
	public News newsDisp(int id);
	public int allNewsCont();
	public List<News> allNewsList(int page);
	public boolean newsDelete(int id);
	public int newsAdd(News user);
	public boolean newsUpdate(News user);
	public News newsDisp(String name,String pwd);		//find user by name and pwd
	public boolean newsExists(String title);
	public boolean NewsExists2(int queryid);
}
