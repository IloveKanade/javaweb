package com.mvc.model;

public class User {

	private int id;
	private String name;
	private String pwd;
	private int type;	
	private String udate_at;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + ", type=" + type + ", udate_at=" + udate_at + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUdate_at() {
		return udate_at;
	}
	public void setUdate_at(String udate_at) {
		this.udate_at = udate_at;
	}
}
