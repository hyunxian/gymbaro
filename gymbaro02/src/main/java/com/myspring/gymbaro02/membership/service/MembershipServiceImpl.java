package com.myspring.gymbaro02.membership.service;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.gymbaro02.membership.dao.MembershipDAO;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service("membershipService")
public class MembershipServiceImpl implements MembershipService {
	@Autowired
	private MembershipDAO membershipDAO;
	
	public String addNewMembership(Map<String,Object> membershipMap) throws Exception {
		String membership_id = membershipDAO.insertNewMembership(membershipMap);
		
		//���������� db�� ȸ���� �߰� �� ����ڿ��� ���� ���� �޼��� �����ֱ�
		membershipResultMessage(membershipMap, membership_id);
		return membership_id;
	}
	
	public void membershipResultMessage(Map<String, Object> membershipMap, String membership_id) {

        String api_key = "NCSFYSP5ZGLEHJYA"; // api key
        String api_secret = "SIDVIQGE0KCAAN0LYTXA6OQJXAM0FMH9"; // api secret
        Message coolsms = new Message(api_key, api_secret);
        
        String phoneNumber = (String) membershipMap.get("phone_number");
        phoneNumber = phoneNumber.replaceAll("-","");
        
        String name = (String) membershipMap.get("name");
        String start_date = (String) membershipMap.get("start_date");
        
        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // ������ȭ��ȣ
        params.put("from", "01030279772");    // �߽���ȭ��ȣ. �׽�Ʈ�ÿ��� �߽�,���� �Ѵ� ���� ��ȣ�� �ϸ� ��
        params.put("type", "SMS");
        params.put("text", "[���ٷ� ȸ���� ���� ����] �̸� : " + name + ", ȸ����ȣ : " + membership_id + ", ������� : " + start_date);
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }
}
