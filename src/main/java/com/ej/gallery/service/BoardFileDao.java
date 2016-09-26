package com.ej.gallery.service;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardFileDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	final String NS ="com.ej.gallery.service.BoardFileMapper";
	
	public int deleteBoardFileByKey(HashMap filesNo) {
		//업데이트 할 때 한 개 또는 여러개를 삭제하기 위해 만든 쿼리문 (where절에 사용하는 값을 boardFileNo)
		return sqlSession.delete(NS+".deleteBoardFileByKey", filesNo);
	}
	
	public int deleteBoardFileByFK(int boardArticleNo) {
		//한 개의 데이터를 삭제를 하기 위해 만든 쿼리문(where절에 사용하는 값을 boardArticleNo)
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
