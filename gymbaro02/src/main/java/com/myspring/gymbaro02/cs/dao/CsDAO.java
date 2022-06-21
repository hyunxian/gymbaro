package com.myspring.gymbaro02.cs.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.gymbaro02.cs.vo.CsCommentVO;
import com.myspring.gymbaro02.cs.vo.CsVO;

public interface CsDAO {
	public void addNewcs(Map<String,Object> map) throws DataAccessException; //���Ǳ� �߰� 
	public List<CsVO> selectCS() throws DataAccessException; //���Ǳ� ��� ��ȸ
	public CsVO csDetail(String csNO) throws DataAccessException; //���Ǳ� ��
	public void updateCS(Map<String,Object> CsMap) throws DataAccessException; //���Ǳ� ����
	public void deleteCS(String csNO) throws DataAccessException; //���Ǳ� ���� 
	public CsCommentVO cscommentlist(String csNO) throws Exception; //�亯 ��ȸ 
	public void insertCsComment(CsCommentVO cscommentVO) throws DataAccessException; //�亯 ���
	public void csCommentModify(CsCommentVO cscommentVO) throws Exception; //�亯 ���� 
	public void csCommentDelete(String cs_commentNO) throws Exception; //�亯 ���� 
}
