package com.ej.gallery.web;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="/multipart", method = RequestMethod.POST)
	public String multipart(MultipartFile image, HttpServletRequest request) {	//@RequestParam("image") String image -> 파일의 이름만 넘어온다.
		if(!image.isEmpty()) {
			//파일 분석
			logger.info("넘어온 파일 이름 : {}", image.getOriginalFilename());
			logger.info("넘어온 파일 태그 이름 : {}", image.getName());
			logger.info("넘어온 파일 컨텐트 타입 : {}", image.getContentType());
			logger.info("넘어온 파일 용량 : {}", image.getSize());
		
			//파일 저장
			//저장 디렉토리
			String saveDir = "D:\\leeeunjin\\jjdev_spring\\gallery\\src\\main\\webapp\\upload";
			//String saveDir = request.getServletContext().getRealPath("resources");
			logger.info("넘어온 파일 경로 : {}", saveDir);
			//저장할 파일 이름
			UUID uuid = UUID.randomUUID();
			String saveFileName = uuid.toString().replace("-", "");
			logger.info("파일 이름 : {}", saveFileName);
			//확장자
			String saveType = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".")+1);
			String fullFileName = saveDir + "\\" + saveFileName + "." + saveType;
			logger.info("최종 파일 이름 : {}", fullFileName);
			
			File saveFile = new File(fullFileName);
			try {
				image.transferTo(saveFile);
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/multipart", method = RequestMethod.GET)
	public String multipart() {
		return "multipart";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
}