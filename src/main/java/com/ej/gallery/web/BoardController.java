package com.ej.gallery.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.ej.gallery.service.BoardFile;
import com.ej.gallery.service.BoardFileForDelete;
import com.ej.gallery.service.BoardRequest;
import com.ej.gallery.service.BoardService;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdate(Model model, BoardFileForDelete boardFileForDelete) {
		logger.info("model : {}", model.toString());
		logger.info("매개변수 확인 : {}", boardFileForDelete.toString());
		
		return null;
	}
	
	@RequestMapping(value="/boardUpdateForm", method=RequestMethod.GET)
	public String boardUpdateForSelect(Model model, int boardArticleNo) {
		logger.info("업데이트할 boardArticleNo : {}", boardArticleNo);
		Map<String, Object> map = boardService.getBoardDetail(boardArticleNo);
		model.addAttribute("map", map);
		
		return "boardUpdateForm";
	}
	
	@RequestMapping(value="/boardDelete", method=RequestMethod.GET)
	public String boardDelete(int boardArticleNo) {
		logger.info("삭제할 boardArticleNo : {}", boardArticleNo);
		//디렉토리에 있는 파일을 삭제하기 위해 임시로 파일의 이름과 확장자를 저장해놓는 메서드 호출
		List<BoardFile> files = boardService.getBoardFileNameExt(boardArticleNo);
		
		//DB에있는 데이터를 삭제하기 위한 메서드 호출
		boardService.getBoardDelete(files, boardArticleNo);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/boardDetail", method=RequestMethod.GET)
	public String boardDetail(Model model, int boardArticleNo) {
		Map<String, Object> map = boardService.getBoardDetail(boardArticleNo);
		model.addAttribute("map", map);
		//model.addAttribute("boardArticle", map.get("boardArticle"));
		//model.addAttribute("boardFiles", map.get("boardFiles"));
		
		return "boardDetail";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String boardList(Model model) {
		model.addAttribute("boardArticleList", boardService.getBoardArticleList());
		
		return "boardList";
	}
	
	@RequestMapping(value="/boardAdd", method=RequestMethod.GET)
	public String boardAdd() {
		return "boardAdd";
	}
	
	@RequestMapping(value="/boardAdd", method=RequestMethod.POST)
	public String boardAdd(Model model, BoardRequest boardRequest) {
		logger.info("매개변수 확인 : {}", boardRequest.toString());
		
		List<MultipartFile> boardImages = boardRequest.getBoardImage();
		if(boardImages != null) {	//파일은 들어왔으나
			for(MultipartFile multiFile : boardImages) {
				if(!(multiFile.getContentType().equals("image/png")) &&
					!(multiFile.getContentType().equals("image/gif")) &&
					!(multiFile.getContentType().equals("image/jpeg"))) {
					logger.info("if문");
					//png, gif, jpeg가 아니면 다시 boardAdd로 포워드 시킨다. 
					model.addAttribute("msg", "그림파일만 업로드 가능!");
					
					return "boardAdd";
				}
			}
		}
		
		//실제경로를 구하려면 request를 활용해야하기 때문에 controller에서 처리한 뒤 service로 값을 넘긴다.
		String saveDir = "D:\\leeeunjin\\jjdev_spring\\gallery_ej\\src\\main\\webapp\\upload";
		
		boardService.addBoard(boardRequest, saveDir);
		
		return "redirect:/";
	}
}