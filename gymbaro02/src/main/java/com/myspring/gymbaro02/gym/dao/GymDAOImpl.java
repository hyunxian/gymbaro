package com.myspring.gymbaro02.gym.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.gymbaro02.gym.vo.GymImageFileVO;
import com.myspring.gymbaro02.gym.vo.GymVO;
import com.myspring.gymbaro02.liked.vo.LikedVO;

@Repository("gymDAO")
public class GymDAOImpl implements GymDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// ��õ �ü� ����Ʈ ��ȸ
	@Override
	public List<GymVO> selectRecommandGymsList() throws DataAccessException {
		List<GymVO> gymList = sqlSession.selectList("mapper.gym.selectRecommandGymList");		
		return gymList;
	}
	
	// �ֺ� �ü� ����Ʈ ��ȸ
	@Override
	public List<GymVO> selectLocationGymsList(Map<String, Object> condMap) throws DataAccessException {
		List<GymVO> gymList = sqlSession.selectList("mapper.gym.selectLocationGymList", condMap);		
		return gymList;
	}
	
	// �ü� �� ���� ��ȸ
	@Override
	public GymVO selectGymDetail(String gym_id) throws DataAccessException {
		GymVO gymVO = sqlSession.selectOne("mapper.gym.selectGymDetail", gym_id);
		return gymVO;
	}
	
	// �ü� �� ���� ��ȸ
	@Override
	public List<GymImageFileVO> selectGymDetailImage(String gym_id) throws DataAccessException {
		List<GymImageFileVO> imageList = sqlSession.selectList("mapper.gym.selectGymDetailImage", gym_id);
		return imageList;
	}
	
	// �ش� �ü� �� �ش� ���� Ȯ��
	@Override
	public int likedState(LikedVO likedVO) throws DataAccessException {
		int likedState = sqlSession.selectOne("mapper.gym.likedState", likedVO);
		return likedState;
	}
}
