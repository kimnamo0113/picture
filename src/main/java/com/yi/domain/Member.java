package com.yi.domain;

import java.util.Date;

public class Member {
	private String userid;
	private String userpw;
	private String username;
	private String userphone;
	private String email;
	private Date regdate;
	private Date updatedate;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(String userid, String userpw, String username, String userphone, String email, Date regdate,
			Date updatedate) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.userphone = userphone;
		this.email = email;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	@Override
	public String toString() {
		return "Member [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", userphone=" + userphone
				+ ", email=" + email + ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}
	
	
}
