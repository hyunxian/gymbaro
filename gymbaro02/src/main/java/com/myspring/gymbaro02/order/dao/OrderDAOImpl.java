package com.myspring.gymbaro02.order.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.gymbaro02.cart.vo.CartVO;
import com.myspring.gymbaro02.order.vo.OrderVO;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws DataAccessException{
		List<OrderVO> orderGoodsList=new ArrayList<OrderVO>();
		orderGoodsList=(ArrayList)sqlSession.selectList("mapper.order.selectMyOrderList",orderVO);
		return orderGoodsList;
	}
	
	// �ֹ� ������ �߰��ϱ�
	@Override
	public String insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException {
		String order_id= selectOrderID();
		for(int i=0; i<myOrderList.size();i++){
			OrderVO orderVO =(OrderVO)myOrderList.get(i);
			orderVO.setOrder_id(order_id);
			sqlSession.insert("mapper.order.insertNewOrder", orderVO);
		}
		return order_id;
	}
	
	// ����Ʈ ��� �� ���� ����Ʈ���� ������Ű��
	@Override
	public void minusUsePoint(Map pointMap) throws DataAccessException {
		int member_point = sqlSession.selectOne("mapper.order.selectMemberPoint", pointMap);
		int usePoint = Integer.parseInt((String)pointMap.get("usePoint"));
		member_point = member_point - usePoint;
		pointMap.put("member_point", member_point);
		sqlSession.update("mapper.order.minusUsePoint", pointMap);
	}
	// �ֹ� ���̺��� �ֹ� ���������� ��ٱ��Ͽ��� ��ǰ ����
	@Override
	public void deleteCartItem(List<CartVO> deleteList) throws DataAccessException {
		sqlSession.delete("mapper.order.deleteCartItem", deleteList);
	}

	
	private String selectOrderID() throws DataAccessException{
		return sqlSession.selectOne("mapper.order.selectOrderID");		
	}
}
