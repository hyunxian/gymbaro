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
import com.myspring.gymbaro02.gym.vo.GymImageFileVO;
import com.myspring.gymbaro02.notice.vo.NoticeImageFileVO;


public abstract class BaseController {
	private static final String CURR_IMAGE_REPO_PATH_GOODS = "C:\\gymbaro_img\\goods";
	private static final String CURR_IMAGE_REPO_PATH_GYMS = "C:\\gymbaro_img\\gyms";
	private static final String CURR_IMAGE_REPO_PATH_NOTICE = "C:\\gymbaro_img\\notice";
	
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
			
			File file = new File(CURR_IMAGE_REPO_PATH_GOODS +"\\"+ fileName);
			if(mFile.getSize()!=0){ //File Null Check
				if(! file.exists()){ //��λ� ������ �������� ���� ���
					if(file.getParentFile().mkdirs()){ //��ο� �ش��ϴ� ���丮���� ����
							file.createNewFile(); //���� ���� ����
					}
				}
				mFile.transferTo(new File(CURR_IMAGE_REPO_PATH_GOODS +"\\"+"temp"+ "\\"+originalFileName)); //�ӽ÷� ����� multipartFile�� ���� ���Ϸ� ����
			}
		}
		return fileList;
	}
	
	//���� ���ε�
		protected List<GymImageFileVO> uploadGym(MultipartHttpServletRequest multipartRequest) throws Exception{
			List<GymImageFileVO> fileList= new ArrayList<GymImageFileVO>();
			Iterator<String> fileNames = multipartRequest.getFileNames();
			while(fileNames.hasNext()){
				GymImageFileVO imageFileVO = new GymImageFileVO();
				String fileName = fileNames.next();
				imageFileVO.setFileType(fileName);
				MultipartFile mFile = multipartRequest.getFile(fileName);
				String originalFileName=mFile.getOriginalFilename();
				imageFileVO.setFileName(originalFileName);
				fileList.add(imageFileVO);
				
				File file = new File(CURR_IMAGE_REPO_PATH_GYMS +"\\"+ fileName);
				if(mFile.getSize()!=0){ //File Null Check
					if(! file.exists()){ //��λ� ������ �������� ���� ���
						if(file.getParentFile().mkdirs()){ //��ο� �ش��ϴ� ���丮���� ����
								file.createNewFile(); //���� ���� ����
						}
					}
					mFile.transferTo(new File(CURR_IMAGE_REPO_PATH_GYMS +"\\"+"temp"+ "\\"+originalFileName)); //�ӽ÷� ����� multipartFile�� ���� ���Ϸ� ����
				}
			}
			return fileList;
		}
		
		// �������� ���� ���ε�
		protected List<NoticeImageFileVO> uploadNotice(MultipartHttpServletRequest multipartRequest) throws Exception{
			List<NoticeImageFileVO> imageFileList= new ArrayList<NoticeImageFileVO>();
			Iterator<String> fileNames = multipartRequest.getFileNames();
			while(fileNames.hasNext()){
				NoticeImageFileVO noticeImageFileVO = new NoticeImageFileVO();
				String fileName = fileNames.next();
				
				MultipartFile mFile = multipartRequest.getFile(fileName);
				String originalFileName=mFile.getOriginalFilename();
				noticeImageFileVO.setImageFileName(originalFileName);
				imageFileList.add(noticeImageFileVO);
				
				File file = new File(CURR_IMAGE_REPO_PATH_NOTICE +"\\"+ fileName);
				if(mFile.getSize()!=0){ //File Null Check
					if(! file.exists()){ 
						if(file.getParentFile().mkdirs()){ 
								file.createNewFile(); 
						}
					}
					mFile.transferTo(new File(CURR_IMAGE_REPO_PATH_NOTICE +"\\"+"temp"+ "\\"+originalFileName)); 
				}
			}
			return imageFileList;
		}	
	
	//�̹��� ���� ����
	private void deleteFile(String fileName) {
		File file = new File(CURR_IMAGE_REPO_PATH_GOODS+"\\"+fileName);
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
