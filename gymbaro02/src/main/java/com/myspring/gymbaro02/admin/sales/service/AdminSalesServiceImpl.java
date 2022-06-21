package com.myspring.gymbaro02.admin.sales.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.gymbaro02.admin.sales.dao.AdminSalesDAO;
import com.myspring.gymbaro02.admin.sales.vo.SalesVO;

@Service("adminSalesService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminSalesServiceImpl implements AdminSalesService {
	@Autowired
	private AdminSalesDAO adminSalesDAO;
	
	// ���� ���θ� ��Ȳ �׷��� ������ ���� (���� ���� �� ȸ��, �ֹ�, ���� ���� ��Ȳ)
	@Override
	public Map<String, List<SalesVO>> shopGraphData() throws Exception {
		Map<String, List<SalesVO>> totalMap = new HashMap<String, List<SalesVO>>();
		List<SalesVO> memberCountList = adminSalesDAO.selectMemberCount();
		List<SalesVO> orderCountList = adminSalesDAO.selectOrderCount();
		List<SalesVO> membershipCountList = adminSalesDAO.selectMembershipCount();
		List<SalesVO> allSalesList = adminSalesDAO.selectAllSales();
		totalMap.put("memberCountList", memberCountList);
		totalMap.put("orderCountList", orderCountList);
		totalMap.put("membershipCountList", membershipCountList);
		totalMap.put("allSalesList", allSalesList);
		return totalMap;
	}
	
	// ���� ���� ��ȸ
	@Override
	public Map<String, Object> selectSalesList() throws Exception {
		Map<String, Object> salesMap = new HashMap<String, Object>();
		List<SalesVO> orderSalesList = adminSalesDAO.selectOrderSales();
		List<SalesVO> membershipSalesList = adminSalesDAO.selectMembershipSales();
		List<SalesVO> orderDaySales = adminSalesDAO.selectOrderDaySales();
		List<SalesVO> membershipDaySales = adminSalesDAO.selectMembershipDaySales();
		salesMap.put("orderSalesList", orderSalesList);
		salesMap.put("membershipSalesList", membershipSalesList);
		salesMap.put("orderDaySales", orderDaySales);
		salesMap.put("membershipDaySales", membershipDaySales);
		return salesMap;
	}
}
