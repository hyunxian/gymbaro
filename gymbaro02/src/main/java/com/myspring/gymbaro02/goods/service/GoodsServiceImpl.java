package com.myspring.gymbaro02.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.gymbaro02.cs.vo.CsCommentVO;
import com.myspring.gymbaro02.cs.vo.CsVO;
import com.myspring.gymbaro02.goods.dao.GoodsDAO;
import com.myspring.gymbaro02.goods.vo.GoodsImageFileVO;
import com.myspring.gymbaro02.goods.vo.GoodsOptionVO;
import com.myspring.gymbaro02.goods.vo.GoodsReviewVO;
import com.myspring.gymbaro02.goods.vo.GoodsVO;

@Service("goodsService")
@Transactional(propagation=Propagation.REQUIRED)
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDAO goodsDAO;
	
	//��ǰ ����Ʈ
	@Override
	public Map<String,List<GoodsVO>> listGoods(Map<String,Object> condMap) throws Exception {
		Map<String,List<GoodsVO>> goodsMap=new HashMap<String,List<GoodsVO>>();
		List<GoodsVO> goodsList = goodsDAO.selectGoodsList("all", condMap);
		goodsMap.put("all", goodsList);
		// �ｺ ��ǰ ����Ʈ
		goodsList = goodsDAO.selectGoodsList("�ｺ", condMap);
		goodsMap.put("health", goodsList);
		// �ʶ��׽� ��ǰ ����Ʈ
		goodsList=goodsDAO.selectGoodsList("�ʶ��׽�", condMap);
		goodsMap.put("yoga",goodsList);
		// ���� ��ǰ ����Ʈ
		goodsList=goodsDAO.selectGoodsList("����", condMap);
		goodsMap.put("boxing",goodsList);
		// ���� ��ǰ ����Ʈ
		goodsList=goodsDAO.selectGoodsList("����", condMap);
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
		List<CsVO> goodsCsList = goodsDAO.selectGoodsCS(_goods_id); //���Ǳ�
		goodsMap.put("goodsCsList", goodsCsList);
		List<CsCommentVO> commentList = goodsDAO.GoodsCsComment(); //�亯 
		goodsMap.put("commentList", commentList);
		return goodsMap;
	}
	
	@Override
	public void addNewReview(GoodsReviewVO goodsReviewVO) throws Exception {
		goodsDAO.addNewReview(goodsReviewVO);
	}
	
	@Override
	public List<GoodsReviewVO> viewAll(String goods_id) throws Exception {
		return goodsDAO.viewAll(goods_id);
	}
	
	@Override
	public double getScoreAvg(String goods_id) throws Exception {
		return goodsDAO.getScoreAvg(goods_id);
	}
	
	@Override
	public int getReviewCount(String goods_id) throws Exception {
		return goodsDAO.getReviewCount(goods_id);
	}
	
	@Override
	public int countScore1(String goods_id) throws Exception {
		return goodsDAO.countScore1(goods_id);
	}
	@Override
	public int countScore2(String goods_id) throws Exception {
		return goodsDAO.countScore2(goods_id);
	}
	@Override
	public int countScore3(String goods_id) throws Exception {
		return goodsDAO.countScore3(goods_id);
	}
	@Override
	public int countScore4(String goods_id) throws Exception {
		return goodsDAO.countScore4(goods_id);
	}
	@Override
	public int countScore5(String goods_id) throws Exception {
		return goodsDAO.countScore5(goods_id);
	}
	
	@Override
	public void updateReviewState(Map updateReviewStateMap) throws Exception {
		goodsDAO.updateReviewState(updateReviewStateMap);
	}
	
	@Override
	public String getReviewInfo(Map reviewInfoMap) throws Exception {
		return goodsDAO.getReviewInfo(reviewInfoMap);
	}

}
