package com.myspring.gymbaro02.admin.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.gymbaro02.event.vo.EventVO;
import com.myspring.gymbaro02.goods.vo.GoodsImageFileVO;
import com.myspring.gymbaro02.goods.vo.GoodsOptionVO;
import com.myspring.gymbaro02.goods.vo.GoodsVO;

@Repository("adminGoodsDAO")
public class AdminGoodsDAOImpl implements AdminGoodsDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// ��ǰ ����Ʈ ��������
	@Override
	public List<GoodsVO> selectGoodsList() throws DataAccessException {
		List<GoodsVO> listGoods = sqlSession.selectList("mapper.admin.goods.selectGoodsList");
		return listGoods;
	}
	
	// ��ǰ�� ������ �̺�Ʈ ����Ʈ ��������
	@Override
	public List<EventVO> selectEventList() throws DataAccessException {
		List<EventVO> listEvent = sqlSession.selectList("mapper.admin.goods.selectEventList");
		return listEvent;
	}
	
	// ����ǰ ����ϱ�
	@Override
	public int insertNewGoods(Map<String,Object> goodsMap) throws DataAccessException {
		GoodsVO goodsVO = (GoodsVO) goodsMap.get("goodsVO");
		sqlSession.insert("mapper.admin.goods.insertNewGoods", goodsVO);
		int goods_id = goodsVO.getGoods_id();
		return goods_id;
	}
	
	// ����ǰ �ɼ� ����ϱ�
	@Override
	public void insertNewGoodsOption(Map<String,Object> goodsMap, int goods_id) throws DataAccessException {
		List<GoodsOptionVO> goodsOptionList = (List<GoodsOptionVO>) goodsMap.get("goodsOptionList");
		for(int i=0; i<goodsOptionList.size(); i++) {
			GoodsOptionVO goodsOptionVO = goodsOptionList.get(i);
			goodsOptionVO.setGoods_id(goods_id);
			sqlSession.insert("mapper.admin.goods.insertNewGoodsOption", goodsOptionVO);
		}
	}
	
	// ����ǰ �̹��� ����ϱ�
	@Override
	public void insertNewGoodsImage(Map<String,Object> goodsMap, int goods_id) throws DataAccessException {
		List<GoodsImageFileVO> imageFileList = (List<GoodsImageFileVO>) goodsMap.get("goodsImageList");
		for(int i=0; i<imageFileList.size(); i++) {
			GoodsImageFileVO goodsImageFileVO = imageFileList.get(i);
			goodsImageFileVO.setGoods_id(goods_id);
			sqlSession.insert("mapper.admin.goods.insertNewGoodsImage", goodsImageFileVO);
		}
	}
	
	// ��ǰ ���� �����ϱ�
	@Override
	public void updateGoodsState(Map<String,Object> updateGoodsMap) throws DataAccessException {
		sqlSession.update("mapper.admin.goods.updateGoodsState", updateGoodsMap);
	}
	
	// ��ǰ �����ϱ�
	@Override
	public void deleteGoods(List<GoodsVO> deleteList) throws DataAccessException {
		for(int i=0; i<deleteList.size(); i++) {
			GoodsVO goodsVO = deleteList.get(i);
			int goods_id = goodsVO.getGoods_id();
			sqlSession.delete("mapper.admin.goods.deleteGoods", goods_id);
		}
	}
	
	// ��ǰ �� ��ȸ�ϱ�
	@Override
	public GoodsVO selectGoodsDetail(int goods_id) throws DataAccessException {
		GoodsVO goodsVO = sqlSession.selectOne("mapper.admin.goods.selectGoodsDetail", goods_id);
		return goodsVO;
	}
	
	// ��ǰ �� �ɼ� ��ȸ�ϱ�
	@Override
	public List<GoodsOptionVO> selectGoodsOption(int goods_id) throws DataAccessException {
		List<GoodsOptionVO> optionList = sqlSession.selectList("mapper.admin.goods.selectGoodsOption", goods_id);
		return optionList;
	}
	
	// ��ǰ ���̹��� ��ȸ�ϱ�
	@Override
	public List<GoodsImageFileVO> selectGoodsImage(int goods_id) throws DataAccessException {
		List<GoodsImageFileVO> imageList = sqlSession.selectList("mapper.admin.goods.selectGoodsDetailImage", goods_id);
		return imageList;
	}
	
	// ��ǰ ���� �����ϱ�
	@Override
	public void updateGoodsInfo(Map<String, Object> goodsMap) throws DataAccessException {
		sqlSession.update("mapper.admin.goods.updateGoodsInfo", goodsMap);
	}
	
	// ��ǰ ����â -> �ɼ� �߰��ϱ�
	@Override
	public void addGoodsOption(GoodsOptionVO goodsOptionVO) throws DataAccessException {
		sqlSession.insert("mapper.admin.goods.insertNewGoodsOption", goodsOptionVO);
	}
	
	// ��ǰ �ɼ� �����ϱ�
	@Override
	public void updateOption(GoodsOptionVO goodsOptionVO) throws DataAccessException {
		sqlSession.update("mapper.admin.goods.updateOption", goodsOptionVO);
	}
	
	// ��ǰ �̹��� �����ϱ�
	@Override
	public void updateGoodsImage(GoodsImageFileVO goodsImageFileVO) throws DataAccessException {
		sqlSession.update("mapper.admin.goods.updateGoodsImage", goodsImageFileVO);
	}
	
	// ��ǰ �ɼ� �����ϱ�
	@Override
	public void deleteOption(int option_id) throws DataAccessException {
		sqlSession.delete("mapper.admin.goods.deleteOption", option_id);
	}
	
	// ��ǰ �̺�Ʈ �����ϱ� (event_id �÷� ����)
	@Override
	public void updateGoodsEvent(Map<String, Object> updateGoodsMap) throws DataAccessException {
		sqlSession.update("mapper.admin.goods.updateGoodsEvent", updateGoodsMap);
		List<GoodsVO> goodsList = (List<GoodsVO>) updateGoodsMap.get("goodsList"); // ��ǰ ���̺��� �̺�Ʈ ���̵� �� �������ֱ�
		for(int i=0; i<goodsList.size(); i++) { // �̺�Ʈ ���� �������� ���� ��ǰ�� ���ΰ����� �����Ǵ� ���ν��� ȣ��
			GoodsVO goodsVO = goodsList.get(i);
			int goods_id = goodsVO.getGoods_id();
			sqlSession.update("mapper.admin.goods.updateGoodsDiscount", goods_id);
		}
	}
	
	// ��ǰ �̺�Ʈ ���� ����ϱ� (event_id, goods_discount �� ����)
	@Override
	public void deleteGoodsEvent(List<GoodsVO> deleteList) throws DataAccessException {
		 sqlSession.update("mapper.admin.goods.deleteGoodsEvent", deleteList);
	}
}
