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
@Transactional //이 밑은 하나로 보고 예외 발생하면 취소시킨다.
public class BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	@Autowired
	private BoardArticleDao boardArticleDao;
	@Autowired
	private BoardFileDao boardFileDao;
	
	//실제경로를 구하려면 request를 활용해야하기 때문에 controller에서 처리한 뒤 service로 값을 넘긴다.
	//String saveDir = "D:\\leeeunjin\\jjdev_spring\\gallery_ej\\src\\main\\webapp\\upload";
	String saveDir = "C:\\Users\\dbdl0\\git\\eunjin\\src\\main\\webapp\\upload";

	public void modifyBoard(BoardFileForDelete boardFileForDelete, String saveDir) {
		BoardArticle boardArticle = new BoardArticle();
		boardArticle.setBoardArticleNo(boardFileForDelete.getBoardArticleNo());
		boardArticle.setBoardArticleTitle(boardFileForDelete.getBoardTitle());
		boardArticle.setBoardArticleContect(boardFileForDelete.getBoardContent());
		logger.info("boardArticle에 담긴 값 : {}", boardArticle.toString());
		
		boardArticleDao.updateBoardArticleByKey(boardArticle);
		
		int[] boardFilesNo = boardFileForDelete.getBoardFileNo();
		HashMap<String, Object> filesNo = new HashMap<String, Object>();
		filesNo.put("filesNo", boardFilesNo);
		
		boardFileDao.deleteBoardFileByKey(filesNo);
		
		
	}

	public void getBoardDelete(List<BoardFile> boardFiles, int boardArticleNo) {
		//테이블간에 시퀀스(BoardArticle(PK), BoardFile(FK))가 설정되어있기 때문에 BoardFile테이블의 데이터부터 삭제한 뒤 BoardArticle테이블의 데이터 삭제!
		boardFileDao.deleteBoardFileByFK(boardArticleNo);
		boardArticleDao.deleteBoardArticleByKey(boardArticleNo);
		
		//디렉토리에 있는 파일들을 삭제하는 코드
		for(BoardFile boardFile : boardFiles) {
			String fileName = boardFile.getBoardFileName() + "." + boardFile.getBoardFileExt();
			logger.info("삭제할 fileName : {}", fileName);
			logger.info("삭제할 file의 최종 경로 : {}", saveDir + "\\" + fileName);
			
			File file = new File(saveDir + "\\" + fileName);
			if(file.delete()) {
				logger.info("fileName 삭제 완료!");
			} else {
				logger.info("fileName 삭제 실패!");
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
		logger.info("boardArticle에 담긴 값 : {}", boardArticle.toString());
		
		//BoardArticleDao 호출 -> INSERT : 데이터 입력, PrimaryKey 값 가지고와서 BoardArticle.getBoardArticleNo 에 담기
		boardArticleDao.insertBoardArticle(boardArticle);
		int boardArticleNo = boardArticle.getBoardArticleNo();
		
		List<MultipartFile> boardImages = boardRequest.getBoardImage();
		if(boardImages != null) {
			for(MultipartFile multiFile : boardImages) {
				//파일 이름 만들기
				UUID uuid = UUID.randomUUID();
				String saveFileName = uuid.toString().replace("-", "");
				
				BoardFile boardFile = new BoardFile();
				
				//확장자와 사이즈, 마임타입 가져오기
				long size = multiFile.getSize();
				
				int lastIndex = multiFile.getOriginalFilename().lastIndexOf(".")+1;
				String saveType = multiFile.getOriginalFilename().substring(lastIndex);
				saveType = saveType.toLowerCase();
				boardFile.setBoardArticleNo(boardArticleNo);
				boardFile.setBoardFileName(saveFileName);
				boardFile.setBoardFileExt(saveType);
				boardFile.setBoardFileSize(size);
				boardFile.setBoardFileType(multiFile.getContentType());
				
				logger.info("boardFile에 담긴 값 : {}", boardFile.toString());
				
				//저장
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
					
					//BoardFileDao 호출
					boardFileDao.insertBoardFile(boardFile);
				}
			}
		}
	}
}