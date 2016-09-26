package com.ej.gallery.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional //�� ���� �ϳ��� ���� ���� �߻��ϸ� ��ҽ�Ų��.
public class BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	@Autowired
	private BoardArticleDao boardArticleDao;
	@Autowired
	private BoardFileDao boardFileDao;
	
	//������θ� ���Ϸ��� request�� Ȱ���ؾ��ϱ� ������ controller���� ó���� �� service�� ���� �ѱ��.
	//String saveDir = "D:\\leeeunjin\\jjdev_spring\\gallery_ej\\src\\main\\webapp\\upload";
	String saveDir = "C:\\Users\\dbdl0\\git\\eunjin\\src\\main\\webapp\\upload";

	public void modifyBoard(BoardFileForDelete boardFileForDelete, String saveDir) {
		BoardArticle boardArticle = new BoardArticle();
		boardArticle.setBoardArticleNo(boardFileForDelete.getBoardArticleNo());
		boardArticle.setBoardArticleTitle(boardFileForDelete.getBoardTitle());
		boardArticle.setBoardArticleContect(boardFileForDelete.getBoardContent());
		logger.info("boardArticle�� ��� �� : {}", boardArticle.toString());
		
		boardArticleDao.updateBoardArticleByKey(boardArticle);
		
		int[] boardFilesNo = boardFileForDelete.getBoardFileNo();
		HashMap<String, Object> filesNo = new HashMap<String, Object>();
		filesNo.put("filesNo", boardFilesNo);
		
		boardFileDao.deleteBoardFileByKey(filesNo);
		
		
	}

	public void getBoardDelete(List<BoardFile> boardFiles, int boardArticleNo) {
		//���̺��� ������(BoardArticle(PK), BoardFile(FK))�� �����Ǿ��ֱ� ������ BoardFile���̺��� �����ͺ��� ������ �� BoardArticle���̺��� ������ ����!
		boardFileDao.deleteBoardFileByFK(boardArticleNo);
		boardArticleDao.deleteBoardArticleByKey(boardArticleNo);
		
		//���丮�� �ִ� ���ϵ��� �����ϴ� �ڵ�
		for(BoardFile boardFile : boardFiles) {
			String fileName = boardFile.getBoardFileName() + "." + boardFile.getBoardFileExt();
			logger.info("������ fileName : {}", fileName);
			logger.info("������ file�� ���� ��� : {}", saveDir + "\\" + fileName);
			
			File file = new File(saveDir + "\\" + fileName);
			if(file.delete()) {
				logger.info("fileName ���� �Ϸ�!");
			} else {
				logger.info("fileName ���� ����!");
			}
		}
	}
	
	public List<BoardFile> getBoardFileNameExt(int boardArticleNo) {
		return boardFileDao.selectBoardFileNameExtbyFK(boardArticleNo);
	}

	public Map<String, Object> getBoardDetail(int boardArticleNo) {
		BoardArticle boardArticle = boardArticleDao.selectBoardArticleContentByKey(boardArticleNo);
		List<BoardFile> boardFiles = boardFileDao.selectBoardFileByFK(boardArticleNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardArticle", boardArticle);
		map.put("boardFiles", boardFiles);
		
		return map;
	}
	
	public List<BoardArticle> getBoardArticleList() {
		return boardArticleDao.selectBoardArticle();
	}
	
	public void addBoard(BoardRequest boardRequest, String saveDir) {
		BoardArticle boardArticle = new BoardArticle();
		boardArticle.setBoardArticleTitle(boardRequest.getBoardTitle());
		boardArticle.setBoardArticleContect(boardRequest.getBoardContent());
		logger.info("boardArticle�� ��� �� : {}", boardArticle.toString());
		
		//BoardArticleDao ȣ�� -> INSERT : ������ �Է�, PrimaryKey �� ������ͼ� BoardArticle.getBoardArticleNo �� ���
		boardArticleDao.insertBoardArticle(boardArticle);
		int boardArticleNo = boardArticle.getBoardArticleNo();
		
		List<MultipartFile> boardImages = boardRequest.getBoardImage();
		if(boardImages != null) {
			for(MultipartFile multiFile : boardImages) {
				//���� �̸� �����
				UUID uuid = UUID.randomUUID();
				String saveFileName = uuid.toString().replace("-", "");
				
				BoardFile boardFile = new BoardFile();
				
				//Ȯ���ڿ� ������, ����Ÿ�� ��������
				long size = multiFile.getSize();
				
				int lastIndex = multiFile.getOriginalFilename().lastIndexOf(".")+1;
				String saveType = multiFile.getOriginalFilename().substring(lastIndex);
				saveType = saveType.toLowerCase();
				boardFile.setBoardArticleNo(boardArticleNo);
				boardFile.setBoardFileName(saveFileName);
				boardFile.setBoardFileExt(saveType);
				boardFile.setBoardFileSize(size);
				boardFile.setBoardFileType(multiFile.getContentType());
				
				logger.info("boardFile�� ��� �� : {}", boardFile.toString());
				
				//����
				if(!boardRequest.getBoardImage().isEmpty()) {
					String fullFileName = saveDir + "\\" + saveFileName + "." + saveType;
					logger.info("fullFileName : {}", fullFileName);
					
					File saveFile = new File(fullFileName);
					try {
						multiFile.transferTo(saveFile);
						
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					//BoardFileDao ȣ��
					boardFileDao.insertBoardFile(boardFile);
				}
			}
		}
	}
}