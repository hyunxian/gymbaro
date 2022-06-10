package com.myspring.gymbaro02.admin.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.gymbaro02.admin.goods.dao.AdminGoodsDAO;
import com.myspring.gymbaro02.event.vo.EventVO;
import com.myspring.gymbaro02.goods.vo.GoodsImageFileVO;
import com.myspring.gymbaro02.goods.vo.GoodsOptionVO;
import com.myspring.gymbaro02.goods.vo.GoodsVO;

@Service("adminGoodsService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminGoodsServiceImpl implements AdminGoodsService {
	@Autowired
	private AdminGoodsDAO adminGoodsDAO;
	
	// ��ǰ ����Ʈ ��������
	@Override
	public List<GoodsVO> selectGoodsList() throws Exception {
		List<GoodsVO> listGoods = adminGoodsDAO.selectGoodsList();
		return listGoods;
	}
	
	// ��ǰ�� ������ �̺�Ʈ ����Ʈ ��������
	@Override
	public List<EventVO> selectEventList() throws Exception {
		List<EventVO> listEvent = adminGoodsDAO.selectEventList();
		return listEvent;
	}
	
	// ��ǰ �� ��ȸ�ϱ�
	@Override
	public Map<String, Object> detailGoodsInfo (int goods_id) throws Exception {
		Map<String, Object> goodsMap = new HashMap<String, Object>(); // ��ǰ ���� ���� �� ����
		GoodsVO goodsVO = adminGoodsDAO.selectGoodsDetail(goods_id);
		List<GoodsOptionVO> optionList = adminGoodsDAO.selectGoodsOption(goods_id);
		List<GoodsImageFileVO> imageList = adminGoodsDAO.selectGoodsImage(goods_id);
		goodsMap.put("goodsVO", goodsVO);
		goodsMap.put("optionList", optionList);
		goodsMap.put("imageList", imageList);
		
		return goodsMap;
	}
	
	// ����ǰ ����ϱ�
	public int addNewGoods(Map<String, Object> goodsMap) throws Exception {
		int goods_id = adminGoodsDAO.insertNewGoods(goodsMap); // ��ǰ ���� ���
		adminGoodsDAO.insertNewGoodsOption(goodsMap, goods_id); // ��ǰ �ɼ� ���
		adminGoodsDAO.insertNewGoodsImage(goodsMap, goods_id); // ��ǰ �̹��� ���
		return goods_id;
	}
	
	// ��ǰ ���� �����ϱ�
	@Override
	public void updateGoodsState(Map<String,Object> updateGoodsMap) throws Exception {
		adminGoodsDAO.updateGoodsState(updateGoodsMap);
	}
	
	// ��ǰ �����ϱ�
	@Override
	public void deleteGoods(List<GoodsVO> deleteList) throws Exception {
		adminGoodsDAO.deleteGoods(deleteList);
	}
	
	// ��ǰ ���� �����ϱ�
	@Override
	public void modifyGoodsInfo(Map<String,Object> goodsMap) throws Exception {
		adminGoodsDAO.updateGoodsInfo(goodsMap);
	}
	
	// ��ǰ ���� ����â���� �ɼ� �߰��ϱ�
	@Override
	public void addGoodsOption(GoodsOptionVO goodsOptionVO) throws Exception {
		adminGoodsDAO.addGoodsOption(goodsOptionVO);
	}
	
	// ��ǰ �ɼ� �����ϱ�
	@Override
	public void modifyOption(GoodsOptionVO goodsOptionVO) throws Exception {
		adminGoodsDAO.updateOption(goodsOptionVO);
	}
	
	// ��ǰ �̹��� �����ϱ�
	@Override
	public void modifyGoodsImage(GoodsImageFileVO goodsImageFileVO) throws Exception {
		adminGoodsDAO.updateGoodsImage(goodsImageFileVO);
	}
	
	// ��ǰ �ɼ� �����ϱ�
	@Override
	public void deleteOption(int option_id) throws Exception {
		adminGoodsDAO.deleteOption(option_id);
	}
	
	// ��ǰ �̺�Ʈ �����ϱ�
	@Override
	public void updateGoodsEvent(Map<String, Object> updateGoodsMap) throws Exception {
		adminGoodsDAO.updateGoodsEvent(updateGoodsMap);
	}
	
	// ��ǰ �̺�Ʈ ���� ����ϱ�
	@Override
	public void deleteGoodsEvent(List<GoodsVO> deleteList) throws Exception {
		adminGoodsDAO.deleteGoodsEvent(deleteList);
	}
}
