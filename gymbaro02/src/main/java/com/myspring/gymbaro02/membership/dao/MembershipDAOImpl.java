package com.myspring.gymbaro02.membership.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("membershipDAO")
public class MembershipDAOImpl implements MembershipDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// ȸ���� �߰��ϱ�
	public String insertNewMembership(Map<String, Object> membershipMap) throws DataAccessException {
		String membership_id = selectMembershipID(); // ȸ���� ��ȣ ���� �� �ʿ� �߰�
		membershipMap.put("membership_id", membership_id);
		sqlSession.insert("mapper.membership.insertNewMembership", membershipMap);
		
		return membership_id;
	}
	
	// ȸ���� ���̵� ��ȸ �� ����
	private String selectMembershipID() throws DataAccessException{
		return sqlSession.selectOne("mapper.membership.selectMembershipID");		
	}

}
