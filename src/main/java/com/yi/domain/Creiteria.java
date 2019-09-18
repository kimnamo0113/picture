package com.yi.domain;

public class Creiteria {
	private int page; //페이지 번호
	private int perPageNum; //페이지당 보일 게시글 갯수
	
	public Creiteria() {
		page=1;
		perPageNum=15;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	
	public int getPageStart() {
		return (this.page-1)*perPageNum;
	}

	
	@Override
	public String toString() {
		return "Creiteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	
}
