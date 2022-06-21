package com.myspring.gymbaro02.admin.sales.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.gymbaro02.admin.sales.vo.SalesVO;

@Repository("adminSalesDAO")
public class AdminSalesDAOImpl implements AdminSalesDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// �ֱ� 4���� ���� ȸ���� üũ
	@Override
	public List<SalesVO> selectMemberCount() throws DataAccessException {
		List<SalesVO> memberCountList = sqlSession.selectList("mapper.admin.sales.selectMemberCount");
		return memberCountList;
	}
	
	// �ֱ� 4���� ���� �ֹ��� üũ
	@Override
	public List<SalesVO> selectOrderCount() throws DataAccessException {
		List<SalesVO> orderCountList = sqlSession.selectList("mapper.admin.sales.selectOrderCount");
		return orderCountList;
	}
	
	// �ֱ� 4���� ���� ���෮ üũ
	@Override
	public List<SalesVO> selectMembershipCount() throws DataAccessException {
		List<SalesVO> membershipCountList = sqlSession.selectList("mapper.admin.sales.selectMembershipCount");
		return membershipCountList;
	}
	
	// �ֱ� 4���� ���� ���� ���� üũ
	@Override
	public List<SalesVO> selectAllSales() throws DataAccessException {
		List<SalesVO> allSalesList = sqlSession.selectList("mapper.admin.sales.selectAllSales");
		return allSalesList;
	}
	
	// �ֱ� 4���� ���� �ֹ� ���� üũ
	@Override
	public List<SalesVO> selectOrderSales() throws DataAccessException {
		List<SalesVO> orderSalesList = sqlSession.selectList("mapper.admin.sales.selectOrderSales");
		return orderSalesList;
	}
	
	// �ֱ� 4���� ���� ���� ���� üũ
	@Override
	public List<SalesVO> selectMembershipSales() throws DataAccessException {
		List<SalesVO> membershipSalesList = sqlSession.selectList("mapper.admin.sales.selectMembershipSales");
		return membershipSalesList;
	}
	
	// �ֹ� ���ں� ���� üũ
	@Override
	public List<SalesVO> selectOrderDaySales() throws DataAccessException {
		List<SalesVO> orderDaySales = sqlSession.selectList("mapper.admin.sales.selectOrderDaySales");
		return orderDaySales;
	}
	
	// ���� ���ں� ���� üũ
	@Override
	public List<SalesVO> selectMembershipDaySales() throws DataAccessException {
		List<SalesVO> membershipDaySales = sqlSession.selectList("mapper.admin.sales.selectMembershipDaySales");
		return membershipDaySales;
	}

}
