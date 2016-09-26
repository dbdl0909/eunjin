package com.ej.gallery.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardFileForDelete {
	private int[] boardFileNo;
	private int boardArticleNo;
	private String boardTitle;
	private String boardContent;
	private List<MultipartFile> boardImage;
	
	public int[] getBoardFileNo() {
		return boardFileNo;
	}
	public void setBoardFileNo(int[] boardFileNo) {
		this.boardFileNo = boardFileNo;
	}
	public int getBoardArticleNo() {
		return boardArticleNo;
	}
	public void setBoardArticleNo(int boardArticleNo) {
		this.boardArticleNo = boardArticleNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public List<MultipartFile> getBoardImage() {
		return boardImage;
	}
	public void setBoardImage(List<MultipartFile> boardImage) {
		this.boardImage = boardImage;
	}
	
	@Override
	public String toString() {
		return "BoardFileForDelete [boardFileNo.length=" + boardFileNo.length + ", boardArticleNo=" + boardArticleNo + ", boardTitle="
				+ boardTitle + ", boardContent=" + boardContent + ", boardImage=" + boardImage + "]";
	}

}