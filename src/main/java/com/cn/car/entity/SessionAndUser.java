package com.cn.car.entity;

import javax.servlet.http.HttpSession;

public class SessionAndUser {
	private String userId;
	private HttpSession session;
	private String sid;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
}
