package com.ej.gallery.service;

public class BoardFile {
	private int boardFileNo;
	private int boardArticleNo;
	private String boardFileName;
	private String boardFileExt;
	private long boardFileSize;
	private String boardFileType;
	
	public int getBoardFileNo() {
		return boardFileNo;
	}
	public void setBoardFileNo(int boardFileNo) {
		this.boardFileNo = boardFileNo;
	}
	public int getBoardArticleNo() {
		return boardArticleNo;
	}
	public void setBoardArticleNo(int boardArticleNo) {
		this.boardArticleNo = boardArticleNo;
	}
	public String getBoardFileName() {
		return boardFileName;
	}
	public void setBoardFileName(String boardFileName) {
		this.boardFileName = boardFileName;
	}
	public String getBoardFileExt() {
		return boardFileExt;
	}
	public void setBoardFileExt(String boardFileExt) {
		this.boardFileExt = boardFileExt;
	}
	public long getBoardFileSize() {
		return boardFileSize;
	}
	public void setBoardFileSize(long boardFileSize) {
		this.boardFileSize = boardFileSize;
	}
	public String getBoardFileType() {
		return boardFileType;
	}
	public void setBoardFileType(String boardFileType) {
		this.boardFileType = boardFileType;
	}
}
