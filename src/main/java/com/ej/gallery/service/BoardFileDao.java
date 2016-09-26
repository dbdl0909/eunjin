package com.ej.gallery.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardFileDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	final String NS ="com.ej.gallery.service.BoardFileMapper";
	
	public int deleteBoardFileByFK(int boardArticleNo) {
		return sqlSession.delete(NS+".deleteBoardFileByFK", boardArticleNo);
	}
	
	public List<BoardFile> selectBoardFileNameExtbyFK(int boardArticleNo) {
		return sqlSession.selectList(NS+".selectBoardFileNameExtbyFK", boardArticleNo);
	}
	
	public List<BoardFile> selectBoardFileByFK(int boardArticleNo) {
		return sqlSession.selectList(NS+".selectBoardFileByFK", boardArticleNo);
	}
	
	public int insertBoardFile(BoardFile boardFile) {
		return sqlSession.insert(NS+".insertBoardFile", boardFile);
	}
}
