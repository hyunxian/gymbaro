package com.myspring.gymbaro02.order.service;

import java.util.List;
import java.util.Map;

import com.myspring.gymbaro02.cart.vo.CartVO;
import com.myspring.gymbaro02.order.vo.OrderVO;

public interface OrderService {
	
	public String addNewOrder(List<OrderVO> myOrderList) throws Exception; // �ֹ� ������ �߰�
	public void minusUsePoint(Map pointMap) throws Exception; // ��� ����Ʈ ����
	public void deleteCartItem(List<CartVO> deleteList) throws Exception; // �ֹ� �� ��ٱ��Ͽ��� ��ǰ ����
	
}
