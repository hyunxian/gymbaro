package com.myspring.gymbaro02.cs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.gymbaro02.cs.dao.CsDAO;
import com.myspring.gymbaro02.cs.vo.CsCommentVO;
import com.myspring.gymbaro02.cs.vo.CsVO;

@Service("csService")
public class CsServiceImpl implements CsService {
	@Autowired
	private CsDAO csDAO;
	
		//���Ǳ� �߰�
		@Override
		public void addcsList (Map<String,Object> map) throws Exception{
			map.get("uid");
			csDAO.addNewcs(map);

		}
		
		//�� ��� ��ȸ 
		@Override
		public List<CsVO> selectCS() throws Exception{
			List<CsVO> csList = csDAO.selectCS();
			return csList;
		}
		
		//���Ǳ� �� 
		@Override
		public CsVO csDetail(String csNO) throws Exception{
			CsVO csDetail = csDAO.csDetail(csNO);
			return csDetail;
		}
		
		//���Ǳ� ����
		@Override
		public void updateCS (Map<String,Object> CsMap) throws Exception{
			csDAO.updateCS(CsMap);
		}
		
		//���Ǳ� ����
		@Override
		public void deleteCS(String csNO) throws Exception{
			csDAO.deleteCS(csNO);
		}
		
		//�亯 ��ȸ
		@Override
		public CsCommentVO cscommentlist(String csNO) throws Exception{
			return csDAO.cscommentlist(csNO);
		}
		
		//�亯 ��� 
		@Override
		public void insertCsComment(CsCommentVO cscommentVO) throws Exception{
			csDAO.insertCsComment(cscommentVO);
		}
		
		//�亯 ����
		@Override
		public void csCommentModify(CsCommentVO cscommentVO) throws Exception{
			csDAO.csCommentModify(cscommentVO);
		}
		
		//�亯 ���� 
		@Override
		public void csCommentDelete(String cs_commentNO) throws Exception{
			csDAO.csCommentDelete(cs_commentNO);
		}
}
