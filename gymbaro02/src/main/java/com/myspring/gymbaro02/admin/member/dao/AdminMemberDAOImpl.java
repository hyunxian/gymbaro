package com.myspring.gymbaro02.admin.member.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.gymbaro02.member.vo.MemberVO;

@Repository("adminMemberDAO")
public class AdminMemberDAOImpl implements AdminMemberDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// ������ �α���
	@Override
	public MemberVO adminLogin(Map<String,Object> loginMap) throws DataAccessException {
		MemberVO memberVO = sqlSession.selectOne("mapper.admin.member.adminLogin", loginMap);
		return memberVO;
	}
	
	// �α����ϴ� ���̵� �����Ϳ� ������ ��Ʈ�� Ȯ��
	@Override
	public String getSaltById(String id) throws DataAccessException {
		String salt = sqlSession.selectOne("mapper.member.getSaltbyId", id);
		return salt;
	}
	
	// ȸ�� ����Ʈ ��ȸ
	@Override
	public List<MemberVO> listMember() throws DataAccessException {
		List<MemberVO> listMember = new ArrayList<MemberVO>();
		listMember = sqlSession.selectList("mapper.admin.member.selectMemberList");
		return listMember;
	}
	
	// ȸ�� ��� �����ϱ�(������ȣ, ������ ���)
	@Override
	public void updateMemberLevel(Map<String, Object> updateLevelMap) throws DataAccessException {
		sqlSession.update("mapper.admin.member.updateMemberLevel", updateLevelMap);
	}
	
	// ȸ�� ����Ʈ �߰��ϱ�(������ȣ, �߰��� ������)
	@Override
	public void updateMemberPoint(Map<String, Object> updatePointMap) throws DataAccessException {
		sqlSession.update("mapper.admin.member.updateMemberPoint", updatePointMap);
		sqlSession.insert("mapper.admin.member.adminAddPoint", updatePointMap);
	}
	
	// ȸ�� ����(Ż��) - Ż�𿩺� Y�� ����
	@Override
	public void deleteMember(List<MemberVO> deleteList) throws DataAccessException {
		for(int i=0; i<deleteList.size(); i++) {
			System.out.println("����");
			MemberVO memberVO = deleteList.get(i);
			int uid = memberVO.getUid();
			sqlSession.update("mapper.admin.member.deleteMember", uid); // Ż�𿩺� Y�� ����� Ʈ���� �ߵ�
			sqlSession.update("mapper.admin.member.deleteMemberInfo", uid); // Ʈ���� �ߵ� �� �Ϲ� ȸ�� ���̺��� �������� ���� ���ν��� ����
		}
	}
	
	// �ش� ȸ�� ���� ��
	@Override
	public MemberVO selectMemberDetail(int uid) throws DataAccessException {
		MemberVO memberVO = new MemberVO();
		memberVO = sqlSession.selectOne("mapper.admin.member.selectMemberDetail", uid);
		return memberVO;
	}

}
