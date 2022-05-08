package com.myspring.gymbaro02.order.service;

import java.util.List;
import java.util.Map;

import com.myspring.gymbaro02.order.vo.OrderVO;

public interface OrderService {
	
	public String addNewOrder(List<OrderVO> myOrderList) throws Exception; // 주문 데이터 추가
	public void minusUsePoint(Map pointMap) throws Exception; // 사용 포인트 차감
	
}
