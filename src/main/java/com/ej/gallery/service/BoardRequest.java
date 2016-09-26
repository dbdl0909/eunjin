package com.ej.gallery.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardRequest {
	private String boardTitle;
	private String boardContent;
	private List<MultipartFile> boardImage;
	
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
		return "BoardRequest [boardTitle=" + boardTitle + ", boardContent=" + boardContent + ", boardImage="
				+ boardImage + "]";
	}
}
