package com.myspring.gymbaro02.member.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.gymbaro02.member.vo.MemberVO;

@Repository
public class MemberRepository {
	// mapper�� ȣ���ϱ� ���� Ŭ���� ����.
		@Autowired
		private SqlSession sqlSession;
		
		// ���� ����
		public void kakaoinsert(HashMap<String, Object> userInfo) {
			sqlSession.insert("mapper.member.kakaoInsert",userInfo);
		}

		// ���� Ȯ��
		public MemberVO findkakao(HashMap<String, Object> userInfo) {
			System.out.println("RN:"+userInfo.get("nickname"));
			System.out.println("RE:"+userInfo.get("email"));
			return sqlSession.selectOne("mapper.member.findKakao", userInfo);
		}
}
