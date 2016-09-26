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
import com.ej.gallery.util.BoardContentTypeHelper;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardContentTypeHelper BoardContentTypeHelper;
	
	//������θ� ���Ϸ��� request�� Ȱ���ؾ��ϱ� ������ controller���� ó���� �� service�� ���� �ѱ��.
	//String saveDir = "D:\\leeeunjin\\jjdev_spring\\gallery_ej\\src\\main\\webapp\\upload";
	String saveDir = "C:\\Users\\dbdl0\\git\\eunjin\\src\\main\\webapp\\upload";
	
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdate(Model model, BoardFileForDelete boardFileForDelete) {
		logger.info("�Ű����� Ȯ�� : {}", boardFileForDelete.toString());
		
		List<MultipartFile> boardImages = boardFileForDelete.getBoardImage();
		String msg = BoardContentTypeHelper.typeHelper(boardImages);
		if(msg.equals("�׸����ϸ� ���ε� ����!")) {
			model.addAttribute("msg", msg);
			return "boardUpdateForm";
		}
		
		boardService.modifyBoard(boardFileForDelete, saveDir);
		return null;
	}
	
	@RequestMapping(value="/boardUpdateForm", method=RequestMethod.GET)
	public String boardUpdateForSelect(Model model, int boardArticleNo) {
		logger.info("������Ʈ�� boardArticleNo : {}", boardArticleNo);
		Map<String, Object> map = boardService.getBoardDetail(boardArticleNo);
		model.addAttribute("map", map);
		
		return "boardUpdateForm";
	}
	
	@RequestMapping(value="/boardDelete", method=RequestMethod.GET)
	public String boardDelete(int boardArticleNo) {
		logger.info("������ boardArticleNo : {}", boardArticleNo);
		//���丮�� �ִ� ������ �����ϱ� ���� �ӽ÷� ������ �̸��� Ȯ���ڸ� �����س��� �޼��� ȣ��
		List<BoardFile> files = boardService.getBoardFileNameExt(boardArticleNo);
		
		//DB���ִ� �����͸� �����ϱ� ���� �޼��� ȣ��
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
		logger.info("�Ű����� Ȯ�� : {}", boardRequest.toString());
		
		List<MultipartFile> boardImages = boardRequest.getBoardImage();
		/*
		if(boardImages != null) {	//������ ��������
			for(MultipartFile multiFile : boardImages) {
				if(!(multiFile.getContentType().equals("image/png")) &&
					!(multiFile.getContentType().equals("image/gif")) &&
					!(multiFile.getContentType().equals("image/jpeg"))) {
					
					//png, gif, jpeg�� �ƴϸ� �ٽ� boardAdd�� ������ ��Ų��. 
					model.addAttribute("msg", "�׸����ϸ� ���ε� ����!");
					
					return "boardAdd";
				}
			}
		}*/
		String msg = BoardContentTypeHelper.typeHelper(boardImages);
		if(msg.equals("�׸����ϸ� ���ε� ����!")) {
			model.addAttribute("msg", msg);
			return "boardAdd";
		}
		
		boardService.addBoard(boardRequest, saveDir);
		
		return "redirect:/";
	}
}