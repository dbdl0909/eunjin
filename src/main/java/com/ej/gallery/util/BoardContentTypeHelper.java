package com.ej.gallery.util;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardContentTypeHelper {
	
	public String typeHelper(List<MultipartFile> boardImages) {
		String msg = "";
		
		if(boardImages != null) {	//������ ��������
			for(MultipartFile multiFile : boardImages) {
				if(!(multiFile.getContentType().equals("image/png")) &&
					!(multiFile.getContentType().equals("image/gif")) &&
					!(multiFile.getContentType().equals("image/jpeg"))) {
					
					//png, gif, jpeg�� �ƴϸ� �ٽ� boardAdd�� ������ ��Ų��. 
					msg = "�׸����ϸ� ���ε� ����!";
				}
			}
		}
		return msg;
	}
}
