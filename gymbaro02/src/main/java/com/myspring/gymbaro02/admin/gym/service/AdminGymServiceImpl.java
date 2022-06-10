package com.myspring.gymbaro02.admin.gym.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.gymbaro02.admin.gym.dao.AdminGymDAO;
import com.myspring.gymbaro02.gym.vo.GymVO;

@Service("adminGymService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminGymServiceImpl implements AdminGymService {
	@Autowired
	private AdminGymDAO adminGymDAO;
	
	// �ü� ��� ��ȸ�ϱ�
	@Override
	public List<GymVO> selectGymList() throws Exception {
		List<GymVO> listGyms = adminGymDAO.selectGymList();
		return listGyms;
	}
	
	// �ü� ���� �����ϱ�
	@Override
	public void updateGymState(Map<String, Object> updateStateMap) throws Exception {
		adminGymDAO.updateGymState(updateStateMap);
	}
	
	// �ü� ��õ���� �����ϱ�
	@Override
	public void updateRecommand(Map<String, Object> updateRecommandMap) throws Exception {
		adminGymDAO.updateRecommand(updateRecommandMap);
	}

}
