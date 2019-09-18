package com.yi.domain;

import java.util.Date;

public class Attach {
	private int bno;
	private String userid;
	private String fullName;
	private String orgName;
	private Date regdate;
	public Attach() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Attach(int bno, String userid, String fullName, String orgName, Date regdate) {
		super();
		this.bno = bno;
		this.userid = userid;
		this.fullName = fullName;
		this.orgName = orgName;
		this.regdate = regdate;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "Attach [bno=" + bno + ", userid=" + userid + ", fullName=" + fullName + ", orgName=" + orgName
				+ ", regdate=" + regdate + "]";
	}
	
	
	
}
