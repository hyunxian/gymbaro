package com.myspring.gymbaro02.admin.membership.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.gymbaro02.admin.membership.dao.AdminMembershipDAO;
import com.myspring.gymbaro02.membership.vo.MembershipVO;

@Service("adminMembershipService")
public class AdminMembershipServiceImpl implements AdminMembershipService {
	@Autowired
	private AdminMembershipDAO adminMembershipDAO;
	
	// ȸ���� ��� ��������
	public List<MembershipVO> listMembership() throws Exception {
		List<MembershipVO> listMembership = adminMembershipDAO.listMembership();
		return listMembership;
	}
	
	// ȸ���� ���� �����ϱ�
	@Override
	public void updateMembershipState(Map<String,Object> updateMembershipMap) throws Exception {
		adminMembershipDAO.updateMembershipState(updateMembershipMap);
	}
		
	// ȸ���� �����ϱ�
	@Override
	public void deleteMembership(List<MembershipVO> deleteList) throws Exception {
		adminMembershipDAO.deleteMembership(deleteList);
	}
	
}
