package com.myspring.gymbaro02.order.service;

import java.util.List;
import java.util.Map;

import com.myspring.gymbaro02.order.vo.OrderVO;

public interface OrderService {
	
	public String addNewOrder(List<OrderVO> myOrderList) throws Exception; // �ֹ� ������ �߰�
	public void minusUsePoint(Map pointMap) throws Exception; // ��� ����Ʈ ����
	
}
