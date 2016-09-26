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
		//������Ʈ �� �� �� �� �Ǵ� �������� �����ϱ� ���� ���� ������ (where���� ����ϴ� ���� boardFileNo)
		return sqlSession.delete(NS+".deleteBoardFileByKey", filesNo);
	}
	
	public int deleteBoardFileByFK(int boardArticleNo) {
		//�� ���� �����͸� ������ �ϱ� ���� ���� ������(where���� ����ϴ� ���� boardArticleNo)
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
