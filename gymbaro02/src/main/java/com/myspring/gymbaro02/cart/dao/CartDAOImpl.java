package com.myspring.gymbaro02.cart.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.gymbaro02.cart.vo.CartVO;
import com.myspring.gymbaro02.goods.vo.GoodsVO;

@Repository("cartDAO")
public class CartDAOImpl implements CartDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean selectCountInCart(CartVO cartVO) throws DataAccessException{
		String result = sqlSession.selectOne("mapper.cart.selectCountInCart", cartVO);
		return Boolean.parseBoolean(result);
	}
	
	// ��ٱ��� �߰�
	@Override
	public void insertGoodsInCart(CartVO cartVO) throws DataAccessException{
		sqlSession.insert("mapper.cart.insertGoodsInCart", cartVO);
	}
	
	// �� ��ٱ��� ����Ʈ
	@Override
	public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException{
		List<CartVO> cartList = (List)sqlSession.selectList("mapper.cart.selectCartList",cartVO);
		return cartList;
	}
	
	// �� ��ٱ��Ͽ� �ش��ϴ� ��ǰ ����Ʈ
	@Override
	public List<GoodsVO> selectGoodsList(List<CartVO> cartList) throws DataAccessException {
		List<GoodsVO> myGoodsList;
		myGoodsList = sqlSession.selectList("mapper.cart.selectGoodsList",cartList);
		return myGoodsList;
	}
	
	// īƮ ���� ����
	@Override
	public void updateCartGoodsQty(CartVO cartVO) throws DataAccessException{
		sqlSession.insert("mapper.cart.updateCartGoodsQty",cartVO);
	}
	
	// ī�� ��ǰ ����
	@Override
	public void deleteCartGoods(List<CartVO> deleteList) throws DataAccessException{
		for(int i=0; i<deleteList.size(); i++) {
			CartVO cartVO = deleteList.get(i);
			int cart_id = cartVO.getCart_id();
			sqlSession.delete("mapper.cart.deleteCartGoods", cart_id);
		}
	}
	
	// ��ȸ�� ��ٱ��� ��ȸ�ϱ�
	@Override
	public List<GoodsVO> selectNonCartList(List<CartVO> cartList) throws DataAccessException{
		List<GoodsVO> myGoodsList = new ArrayList<GoodsVO>();
		for(int i=0; i<cartList.size(); i++) {
			CartVO cartVO = cartList.get(i);
			GoodsVO goodsVO = new GoodsVO();
			goodsVO = sqlSession.selectOne("mapper.cart.non_selectGoodsList", cartVO);
			myGoodsList.add(goodsVO);
			System.out.println("goodsVO item:" + goodsVO.getGoods_fileName());
		}
		return myGoodsList;
	}
}
