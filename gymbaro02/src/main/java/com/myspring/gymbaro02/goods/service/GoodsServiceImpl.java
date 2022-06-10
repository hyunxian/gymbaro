package com.myspring.gymbaro02.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.gymbaro02.goods.dao.GoodsDAO;
import com.myspring.gymbaro02.goods.vo.GoodsImageFileVO;
import com.myspring.gymbaro02.goods.vo.GoodsOptionVO;
import com.myspring.gymbaro02.goods.vo.GoodsVO;

@Service("goodsService")
@Transactional(propagation=Propagation.REQUIRED)
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDAO goodsDAO;
	
	//��ǰ ����Ʈ
	@Override
	public Map<String,List<GoodsVO>> listGoods() throws Exception {
		Map<String,List<GoodsVO>> goodsMap=new HashMap<String,List<GoodsVO>>();
		List<GoodsVO> goodsList = goodsDAO.selectGoodsList("all");
		goodsMap.put("all", goodsList);
		// �ｺ ��ǰ ����Ʈ
		goodsList = goodsDAO.selectGoodsList("�ｺ");
		goodsMap.put("health", goodsList);
		// �ʶ��׽� ��ǰ ����Ʈ
		goodsList=goodsDAO.selectGoodsList("�ʶ��׽�");
		goodsMap.put("yoga",goodsList);
		// ���� ��ǰ ����Ʈ
		goodsList=goodsDAO.selectGoodsList("����");
		goodsMap.put("boxing",goodsList);
		// ���� ��ǰ ����Ʈ
		goodsList=goodsDAO.selectGoodsList("����");
		goodsMap.put("swim",goodsList);
		// ��ŷ ��ǰ ����Ʈ
		goodsList=goodsDAO.selectRankingList();
		goodsMap.put("ranking", goodsList);
		return goodsMap;
	}
	
	//��ǰ ��
	@Override
	public Map goodsDetail(String _goods_id) throws Exception {
		Map goodsMap=new HashMap();
		GoodsVO goodsVO = goodsDAO.selectGoodsDetail(_goods_id); // ��ǰ ������ ��������
		goodsMap.put("goodsVO", goodsVO);
		List<GoodsOptionVO> optionList = goodsDAO.selectGoodsOption(_goods_id); // ��ǰ �ش� �ɼ� ��������
		goodsMap.put("optionList", optionList);
		List<GoodsImageFileVO> imageList =goodsDAO.selectGoodsDetailImage(_goods_id); // ��ǰ ���̹��� ��������
		goodsMap.put("imageList", imageList);
		return goodsMap;
	}

}
