package com.myspring.gymbaro02.admin.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.gymbaro02.admin.member.dao.AdminMemberDAO;
import com.myspring.gymbaro02.member.service.SHA256Util;
import com.myspring.gymbaro02.member.vo.MemberVO;

@Service("adminMemberService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminMemberServiceImpl implements AdminMemberService {
	@Autowired
	private AdminMemberDAO adminMemberDAO;
	
	
	// ������ �α���
	@Override
	public MemberVO adminLogin(Map<String, Object> loginMap) throws Exception {
		// �Է��� ���̵� �ش��ϴ� ��Ʈ�� ã�� �� �ٽ� ��ȣȭ
			String id = (String)loginMap.get("id");
			String pwd = (String)loginMap.get("pwd");
			
			String salt = adminMemberDAO.getSaltById(id);
			if(salt != "" && salt != null) {
				String password = pwd;
				password  = SHA256Util.getEncrypt(password, salt);
			
				loginMap.put("pwd", password);
			}	
			return adminMemberDAO.adminLogin(loginMap);
	}
	
	// ȸ�� ����Ʈ ��ȸ
	@Override
	public List<MemberVO> listMember() throws Exception {
		List<MemberVO> listMember = new ArrayList<MemberVO>();
		listMember = adminMemberDAO.listMember();
		return listMember;
	}
	
	// ȸ�� ��� �����ϱ�
	@Override
	public void updateMemberLevel(Map<String, Object> updateLevelMap) throws Exception {
		adminMemberDAO.updateMemberLevel(updateLevelMap);
	}
	
	// ȸ�� ����Ʈ �����ϱ�
	@Override
	public void updateMemberPoint(Map<String, Object> updatePointMap) throws Exception {
		adminMemberDAO.updateMemberPoint(updatePointMap);
	}
	
	// ȸ�� ����(Ż��)
	@Override
	public void deleteMember(List<MemberVO> deleteList) throws Exception {
		adminMemberDAO.deleteMember(deleteList);
	}
	
	// �ش� ȸ�� ���� ��
	@Override
	public MemberVO selectMemberDetail(int uid) throws Exception {
		MemberVO memberVO = adminMemberDAO.selectMemberDetail(uid);
		return memberVO;
	}

}
