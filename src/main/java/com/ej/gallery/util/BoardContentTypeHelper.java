package com.ej.gallery.util;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardContentTypeHelper {
	
	public String typeHelper(List<MultipartFile> boardImages) {
		String msg = "";
		
		if(boardImages != null) {	//파일은 들어왔으나
			for(MultipartFile multiFile : boardImages) {
				if(!(multiFile.getContentType().equals("image/png")) &&
					!(multiFile.getContentType().equals("image/gif")) &&
					!(multiFile.getContentType().equals("image/jpeg"))) {
					
					//png, gif, jpeg가 아니면 다시 boardAdd로 포워드 시킨다. 
					msg = "그림파일만 업로드 가능!";
				}
			}
		}
		return msg;
	}
}
