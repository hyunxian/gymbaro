package com.myspring.gymbaro02.cart.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.gymbaro02.cart.dao.CartDAO;
import com.myspring.gymbaro02.cart.vo.CartVO;
import com.myspring.gymbaro02.goods.vo.GoodsVO;


@Service("cartService")
@Transactional(propagation = Propagation.REQUIRED)
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDAO cartDAO;
	
	// ��ٱ��Ͽ� �ִ� ��ǰ���� Ȯ���ϱ�
	@Override
	public boolean findCartGoods(CartVO cartVO) throws Exception{
		return cartDAO.selectCountInCart(cartVO);
	}
	
	// ��ٱ��Ͽ� ���
	@Override
	public void addGoodsInCart(CartVO cartVO) throws Exception{
		cartDAO.insertGoodsInCart(cartVO);
	}
	
	// �� ��ٱ��� ����Ʈ ��ȸ
	@Override
	public Map<String, List> myCartList(CartVO cartVO) throws Exception{
		Map<String, List> cartMap = new HashMap<String,List>();
		List<CartVO> myCartList = cartDAO.selectCartList(cartVO);
		if(myCartList.size()==0) {
			return null;
		}
		List<GoodsVO> myGoodsList = cartDAO.selectGoodsList(myCartList);
		cartMap.put("myCartList", myCartList);
		cartMap.put("myGoodsList", myGoodsList);
		return cartMap;
	}
	
	// īƮ ��ǰ ���� �����ϱ�
	@Override
	public boolean modifyCartQty(CartVO cartVO) throws Exception{
		boolean result=true;
		cartDAO.updateCartGoodsQty(cartVO);
		return result;
	}
	
	// īƮ���� ��ǰ ����
	@Override
	public void removeCartGoods(List<CartVO> deleteList) throws Exception{
		cartDAO.deleteCartGoods(deleteList);
	}
	
	// ��ȸ�� ��ٱ��� ��ȸ
	@Override
	public List<GoodsVO> nonCartList(List<CartVO> cartList) throws Exception {
		List<GoodsVO> myGoodsList = cartDAO.selectNonCartList(cartList);
		return myGoodsList;
	}
}
