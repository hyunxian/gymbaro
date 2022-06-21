package com.myspring.gymbaro02.cs.service;

import java.util.List;
import java.util.Map;

import com.myspring.gymbaro02.cs.vo.CsCommentVO;
import com.myspring.gymbaro02.cs.vo.CsVO;

public interface CsService {
	public void addcsList (Map<String,Object> map) throws Exception; //���Ǳ� �߰�
	public List<CsVO> selectCS() throws Exception; //�� ��� ��ȸ 
	public CsVO csDetail(String csNO) throws Exception; //���Ǳ� ��
	public void updateCS (Map<String,Object> CsMap) throws Exception; //���Ǳ� ����
	public void deleteCS(String csNO) throws Exception; //���Ǳ� ����
	public CsCommentVO cscommentlist(String csNO) throws Exception; //�亯 ��ȸ
	public void insertCsComment(CsCommentVO cscommentVO) throws Exception; //�亯 ���
	public void csCommentModify(CsCommentVO cscommentVO) throws Exception; //�亯 ����
	public void csCommentDelete(String cs_commentNO) throws Exception; //�亯 ����
}
