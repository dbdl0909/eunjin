package com.ej.gallery.service;

public class BoardArticle {
	private int boardArticleNo;
	private String boardArticleTitle;
	private String boardArticleContect;
	
	public int getBoardArticleNo() {
		return boardArticleNo;
	}
	public void setBoardArticleNo(int boardArticleNo) {
		this.boardArticleNo = boardArticleNo;
	}
	public String getBoardArticleTitle() {
		return boardArticleTitle;
	}
	public void setBoardArticleTitle(String boardArticleTitle) {
		this.boardArticleTitle = boardArticleTitle;
	}
	public String getBoardArticleContect() {
		return boardArticleContect;
	}
	public void setBoardArticleContect(String boardArticleContect) {
		this.boardArticleContect = boardArticleContect;
	}
	
	@Override
	public String toString() {
		return "BoardArticle [boardArticleNo=" + boardArticleNo + ", boardAriticleTitle=" + boardArticleTitle
				+ ", boardAriticleContect=" + boardArticleContect + "]";
	}
}
