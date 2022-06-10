package com.myspring.gymbaro02.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.gymbaro02.cart.vo.CartVO;
import com.myspring.gymbaro02.order.dao.OrderDAO;
import com.myspring.gymbaro02.order.vo.OrderVO;

@Service("orderService")
@Transactional(propagation=Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDAO;
	
	// �ֹ� ������ �߰�
	@Override
	public String addNewOrder(List<OrderVO> myOrderList) throws Exception{
		String order_id = orderDAO.insertNewOrder(myOrderList); // �ֹ� ���̺� ������ �߰�
		return order_id;
	}
	
	// ����Ʈ ����
	@Override
	public void minusUsePoint(Map pointMap) throws Exception {
		orderDAO.minusUsePoint(pointMap);
	}
	
	// �ֹ� �� ��ٱ��Ͽ��� ��ǰ ����
	@Override
	public void deleteCartItem(List<CartVO> deleteList) throws Exception {
		orderDAO.deleteCartItem(deleteList);
	}
}
