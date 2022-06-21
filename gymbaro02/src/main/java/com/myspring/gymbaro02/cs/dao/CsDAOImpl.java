package com.myspring.gymbaro02.cs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.gymbaro02.cs.vo.CsCommentVO;
import com.myspring.gymbaro02.cs.vo.CsVO;

@Repository("csDAO")
public class CsDAOImpl implements CsDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//���Ǳ� �߰�
		@Override
		public void addNewcs(Map<String,Object> map) throws DataAccessException{
			sqlSession.insert("mapper.cs.addNewcs",map);
		}
		
		//���Ǳ� ��� ��ȸ 
		@Override
		public List<CsVO> selectCS() throws DataAccessException{
			List<CsVO> csList = sqlSession.selectList("mapper.cs.selectCS");
			return csList;
		}
		
		//���Ǳ� ��
		@Override
		public CsVO csDetail(String csNO) throws DataAccessException{
			CsVO csDetail = sqlSession.selectOne("mapper.cs.csDetail", csNO);
			return csDetail;
		}
		
		//���Ǳ� ����
		@Override
		public void updateCS(Map<String,Object> CsMap) throws DataAccessException{
			sqlSession.update("mapper.cs.updateCS",CsMap);
			
		}
		
		//���Ǳ� ����
		@Override
		public void deleteCS(String csNO) throws DataAccessException{
			sqlSession.delete("mapper.cs.deleteCS",csNO);
		}
		
		// �亯 ��ȸ
		@Override
		public CsCommentVO cscommentlist(String csNO) throws Exception{
			return sqlSession.selectOne("mapper.cs.commentList", csNO);
		}
		
		//�亯 ��� 
		@Override
		public void insertCsComment(CsCommentVO cscommentVO) throws DataAccessException{
			sqlSession.insert("mapper.cs.insertCsComment", cscommentVO);
		}
		
		//�亯 ����
		@Override
		public void csCommentModify(CsCommentVO cscommentVO) throws Exception{
			sqlSession.update("mapper.cs.csCommentModify", cscommentVO);
		}
		
		//�亯 ����
		@Override
		public void csCommentDelete(String cs_commentNO) throws Exception{
			sqlSession.delete("mapper.cs.csCommentDelete", cs_commentNO);
		}

}
