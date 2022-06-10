package com.myspring.gymbaro02.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.gymbaro02.cart.vo.CartVO;
import com.myspring.gymbaro02.order.vo.OrderVO;

public interface OrderDAO {

	public String insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException; // �ֹ� ���̺� ������ �߰�
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws DataAccessException; // �� �ֹ����� ����
	public void minusUsePoint(Map pointMap) throws DataAccessException; // ����Ʈ ��������� �����ϱ�
	public void deleteCartItem(List<CartVO> deleteList) throws DataAccessException; // �ֹ� ���̺��� �ֹ� ���������� ��ٱ��Ͽ��� ��ǰ ����
}
