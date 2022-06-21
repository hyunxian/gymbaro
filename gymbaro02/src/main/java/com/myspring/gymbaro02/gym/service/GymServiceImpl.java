package com.myspring.gymbaro02.gym.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.gymbaro02.gym.dao.GymDAO;
import com.myspring.gymbaro02.gym.vo.GymImageFileVO;
import com.myspring.gymbaro02.gym.vo.GymVO;
import com.myspring.gymbaro02.liked.vo.LikedVO;

@Service("gymService")
@Transactional(propagation=Propagation.REQUIRED)
public class GymServiceImpl implements GymService {
	@Autowired
	private GymDAO gymDAO;
	
	//�ü� ����Ʈ ����
	@Override
	public Map<String,List<GymVO>> listGym(Map<String, Object> condMap) throws Exception {
		Map<String,List<GymVO>> gymMap=new HashMap<String,List<GymVO>>();
		List<GymVO> gymRecommandList = gymDAO.selectRecommandGymsList();
		gymMap.put("recommandList", gymRecommandList);
		List<GymVO> gymLocationList = gymDAO.selectLocationGymsList(condMap);
		gymMap.put("locationList", gymLocationList);

		return gymMap;
	}
	
	//�ü� �������� ����
	@Override
	public Map<String,Object> GymDetail(String gym_id, int uid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// �ش� �ü� ������ ��ȸ
		GymVO gymVO = gymDAO.selectGymDetail(gym_id);
		map.put("gymVO", gymVO);
		//
		// �ش� �ü� �� �̹��� ��ȸ
		List<GymImageFileVO> imageList = gymDAO.selectGymDetailImage(gym_id);
		map.put("imageList", imageList);
		//
		// �ش� �ü� �� ���� ��ȸ
		LikedVO likedVO = new LikedVO();
		likedVO.setGym_id(Integer.parseInt(gym_id));
		likedVO.setUid(uid);
		int likedState = gymDAO.likedState(likedVO);
		map.put("likedState", likedState);
		//
		return map;
	}
}
