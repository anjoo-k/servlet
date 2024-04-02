package com.kh.common.vo;

public class PageInfo {
	private int listCount; // 현재 총 게시글 수
	private int currnetPage; //현재 페이지(사용자가 요청한 페이지 = 사용자가 몇페이지인지 준다(프론트에서 보냄))
	private int pageLimit; // 페이지 하단의 페이징 바 갯수
	private int boardLimit; // 한 페이지 내에 보여질 게시글 최대 갯수
	// 위 4개의 값을 기준으로 아래 3개의 값을 구해야함
	
	private int maxPage; // 가장 마지막 페이지(총 페이지의 수)
	private int startPage; // 페이징바의 시작수
	private int endPage; // 페이징바의 마지막 끝 수
	
	
	public PageInfo() {
		super();
	}


	public PageInfo(int listCount, int currnetPage, int pageLimit, int boardLimit, int maxPage, int startPage,
			int endPage) {
		super();
		this.listCount = listCount;
		this.currnetPage = currnetPage;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}
	
	


	public int getListCount() {
		return listCount;
	}


	public void setListCount(int listCount) {
		this.listCount = listCount;
	}


	public int getCurrnetPage() {
		return currnetPage;
	}


	public void setCurrnetPage(int currnetPage) {
		this.currnetPage = currnetPage;
	}


	public int getPageLimit() {
		return pageLimit;
	}


	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}


	public int getBoardLimit() {
		return boardLimit;
	}


	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}


	public int getMaxPage() {
		return maxPage;
	}


	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}


	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


	@Override
	public String toString() {
		return "PageInfo [listCount=" + listCount + ", currnetPage=" + currnetPage + ", pageLimit=" + pageLimit
				+ ", boardLimit=" + boardLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + "]";
	}
	
	
	

}
