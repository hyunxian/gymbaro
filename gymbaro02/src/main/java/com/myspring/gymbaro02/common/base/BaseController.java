package com.myspring.gymbaro02.common.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.gymbaro02.goods.vo.GoodsImageFileVO;


public abstract class BaseController {
	private static final String CURR_IMAGE_REPO_PATH = "C:\\gymbaro_img\\goods";
	
	//���� ���ε�
	protected List<GoodsImageFileVO> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		List<GoodsImageFileVO> fileList= new ArrayList<GoodsImageFileVO>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while(fileNames.hasNext()){
			GoodsImageFileVO imageFileVO =new GoodsImageFileVO();
			String fileName = fileNames.next();
			imageFileVO.setFileType(fileName);
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName=mFile.getOriginalFilename();
			imageFileVO.setFileName(originalFileName);
			fileList.add(imageFileVO);
			
			File file = new File(CURR_IMAGE_REPO_PATH +"\\"+ fileName);
			if(mFile.getSize()!=0){ //File Null Check
				if(! file.exists()){ //��λ� ������ �������� ���� ���
					if(file.getParentFile().mkdirs()){ //��ο� �ش��ϴ� ���丮���� ����
							file.createNewFile(); //���� ���� ����
					}
				}
				mFile.transferTo(new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+ "\\"+originalFileName)); //�ӽ÷� ����� multipartFile�� ���� ���Ϸ� ����
			}
		}
		return fileList;
	}
	
	//�̹��� ���� ����
	private void deleteFile(String fileName) {
		File file = new File(CURR_IMAGE_REPO_PATH+"\\"+fileName);
		try{
			file.delete();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/*.do" ,method={RequestMethod.POST,RequestMethod.GET})
	protected  ModelAndView viewForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	
}