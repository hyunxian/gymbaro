package com.myspring.gymbaro02.admin.community.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.gymbaro02.community.vo.BoardVO;
import com.myspring.gymbaro02.community.vo.CommentVO;
import com.myspring.gymbaro02.cs.vo.CsVO;
import com.myspring.gymbaro02.goods.vo.GoodsReviewVO;
import com.myspring.gymbaro02.notice.vo.NoticeVO;

@Repository("adminCommunityDAO")
public class AdminCommunityDAOImpl implements AdminCommunityDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// �Խñ� ��ü ��ȸ
	@Override
	public List<BoardVO> selectBoardList() throws DataAccessException{
		List<BoardVO> boardList = sqlSession.selectList("mapper.admin.article.selectBoardList");
		return boardList;
	}
	
	// ��� ��ü ��ȸ
	@Override
	public List<CommentVO> selectCommentList() throws DataAccessException{
		List<CommentVO> commentList = sqlSession.selectList("mapper.admin.article.selectCommentList");
		return commentList;
	}
	
	// ������ ��ȸ
	@Override
	public List<NoticeVO> selectNoticeList() throws DataAccessException{
		List<NoticeVO> noticeList = sqlSession.selectList("mapper.admin.article.selectNoticeList");
		return noticeList;
	}
	
	// ���� ��ȸ
	@Override
	public List<GoodsReviewVO> selectReviewList() throws DataAccessException {
		List<GoodsReviewVO> reviewList = sqlSession.selectList("mapper.admin.article.selectReviewList");
		return reviewList;
	}
	
	// ���Ǳ� ��ȸ
	@Override
	public List<CsVO> selectCsList() throws DataAccessException {
		List<CsVO> csList = sqlSession.selectList("mapper.admin.article.selectCsList");
		return csList;
	}
	
	// �Խñ� ���� ó���ϱ�
	@Override
	public void updateBoardHidden(List<BoardVO> hiddenList) throws DataAccessException {
		sqlSession.update("mapper.admin.article.updateBoardHidden", hiddenList);
	}
	
	// �Խñ� ���� �����ϱ�
	@Override
	public void updateBoardView(int articleNo) throws DataAccessException {
		sqlSession.update("mapper.admin.article.updateBoardView", articleNo);
	}
	
	// ��� ���� ó���ϱ�
	@Override
	public void updateCommentHidden(List<CommentVO> hiddenList) throws DataAccessException {
		sqlSession.update("mapper.admin.article.updateCommentHidden", hiddenList);
	}
	
	// ��� ���� �����ϱ�
	@Override
	public void updateCommentView(int commentNo) throws DataAccessException {
		sqlSession.update("mapper.admin.article.updateCommentView", commentNo);
	}
	
	// ������ �����ϱ�
	@Override
	public void deleteNotice(List<NoticeVO> deleteList) throws DataAccessException {
		sqlSession.delete("mapper.admin.article.deleteNotice", deleteList);
	}
	
	// ������ �̹��� �����ϱ�
	@Override
	public void deleteNoticeImage(List<NoticeVO> deleteList) throws DataAccessException {
		sqlSession.delete("mapper.admin.article.deleteNoticeImage", deleteList);
	}
	
}
