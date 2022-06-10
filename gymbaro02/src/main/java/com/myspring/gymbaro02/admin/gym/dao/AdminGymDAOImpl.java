package com.myspring.gymbaro02.admin.gym.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.gymbaro02.gym.vo.GymVO;

@Repository("adminGymDAO")
public class AdminGymDAOImpl implements AdminGymDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<GymVO> selectGymList() throws DataAccessException {
		List<GymVO> listGyms = sqlSession.selectList("mapper.admin.gym.selectGymList");
		return listGyms;
	}
	
	// �ü� ���� �����ϱ�(������ȣ, ������ ����)
	@Override
	public void updateGymState(Map<String, Object> updateStateMap) throws DataAccessException {
		sqlSession.update("mapper.admin.gym.updateGymState", updateStateMap);
	}
	
	// �ü� ��õ���� �����ϱ�(������ȣ, ��������)
	@Override
	public void updateRecommand(Map<String, Object> updateRecommandMap) throws DataAccessException {
		sqlSession.update("mapper.admin.gym.updateRecommand", updateRecommandMap);
	}
}
