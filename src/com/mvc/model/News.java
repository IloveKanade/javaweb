package com.mvc.model;

public class News {
	private int id;
	private String title;
	private String content;
	private String udate_at;
	private String adminname;
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUdate_at() {
		return udate_at;
	}
	public void setUdate_at(String udate_at) {
		this.udate_at = udate_at;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", content=" + content + ", udate_at=" + udate_at
				+ ", adminname=" + adminname + "]";
	}
	
}
