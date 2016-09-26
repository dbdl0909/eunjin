package com.ej.gallery.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardArticleDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	final String NS ="com.ej.gallery.service.BoardArticleMapper";
	
	public int updateBoardArticleByKey(BoardArticle boardArticle) {
		return sqlSession.update(NS+".updateBoardArticleByKey", boardArticle);
	}
	
	public int deleteBoardArticleByKey(int boardArticleNo) {
		return sqlSession.delete(NS+".deleteBoardArticleByKey", boardArticleNo);
	}
	
	public BoardArticle selectBoardArticleContentByKey(int boardArticleNo) {
		return sqlSession.selectOne(NS+".selectBoardArticleContentByKey", boardArticleNo);
	}
	
	public List<BoardArticle> selectBoardArticle() {
		return sqlSession.selectList(NS+".selectBoardArticleList");
	}
	
	public int insertBoardArticle(BoardArticle boardArticle) {
		return sqlSession.insert(NS+".insertBoardArticle", boardArticle);
	}
}
