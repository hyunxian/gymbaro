package com.myspring.gymbaro02.admin.event.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.gymbaro02.event.vo.EventVO;
import com.myspring.gymbaro02.goods.vo.GoodsVO;

@Repository("adminEventDAO")
public class AdminEventDAOImpl implements AdminEventDAO {
	@Autowired
	private SqlSession sqlSession;
	
	
	// �̺�Ʈ ����Ʈ ��ȸ
	@Override
	public List<EventVO> selectEventList() throws DataAccessException {
		List<EventVO> listEvent = sqlSession.selectList("mapper.admin.event.selectEventList");
		return listEvent;
	}
	
	// �� �̺�Ʈ �߰��ϱ�
	@Override
	public void addNewEvent(EventVO eventVO) throws DataAccessException {
		sqlSession.insert("mapper.admin.event.addNewEvent", eventVO);
	}
	
	// �̺�Ʈ �����ϱ�
	@Override
	public void updateEvent(EventVO eventVO) throws DataAccessException{
		sqlSession.update("mapper.admin.event.updateEvent", eventVO);
	}
	
	// �̺�Ʈ ������ ���� ���� Y�϶�, ����� ��ǰ�� �������� �������ִ� ���ν��� ȣ��
	@Override
	public void updateThisGoods(int event_id) throws DataAccessException{
		List<GoodsVO> goodsList = sqlSession.selectList("mapper.admin.event.selectThisEventGoods", event_id);
		for(int i=0; i<goodsList.size(); i++) {
			GoodsVO goodsVO = goodsList.get(i);
			int goods_id = goodsVO.getGoods_id();
			sqlSession.update("mapper.admin.goods.updateGoodsDiscount", goods_id);
		}
	}
	
	// �̺�Ʈ �����ϱ� (�� �Լ� �����ϱ� ���� ���� �ش� �̺�Ʈ�� ����� ��ǰ�� ���ΰ��� �� �̺�Ʈ ���̵� null�� �������ش�)
	public void deleteEvent(List<EventVO> eventList) throws DataAccessException {
		sqlSession.delete("mapper.admin.event.deleteEvent", eventList);
	}
	
	// �̺�Ʈ ������ ��ǰ�� ����� �̺�Ʈ ���� ���� ��Ű��
	public void deleteGoodsDiscount(List<EventVO> eventList) throws DataAccessException {
		for(int i=0; i<eventList.size(); i++) {
			EventVO eventVO = eventList.get(i);
			int event_id = eventVO.getEvent_id();
			List<GoodsVO> goodsList = sqlSession.selectList("mapper.admin.event.selectThisEventGoods", event_id);
			if(goodsList.size() != 0) {
				sqlSession.update("mapper.admin.goods.deleteGoodsEvent", goodsList);
			}
		}
	}
}
