package com.myspring.gymbaro02.member.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myspring.gymbaro02.member.dao.MemberDAO;
import com.myspring.gymbaro02.member.vo.MemberVO;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	
	@Override
	public MemberVO login(Map loginMap) throws Exception{
		
		// �Է��� ���̵� �ش��ϴ� ��Ʈ�� ã�� �� �ٽ� ��ȣȭ
		String id = (String)loginMap.get("id");
		String pwd = (String)loginMap.get("pwd");
		
		String salt = memberDAO.getSaltById(id);
		String password = pwd;
		password  = SHA256Util.getEncrypt(password, salt);
		
		loginMap.put("pwd", password);
		
		return memberDAO.login(loginMap);
	}
	
	@Override
	public void addMember(MemberVO memberVO) throws Exception{
		
		// ��й�ȣ ��ȣȭ �� �ٽ� set
		String salt = SHA256Util.generateSalt();
		memberVO.setSALT(salt);
		String password = memberVO.getMember_pwd();
		password = SHA256Util.getEncrypt(password, salt);

		memberVO.setMember_pwd(password);	
		memberDAO.insertNewMember(memberVO);
	}
	
	@Override
	public String overlapped(String id) throws Exception{
		return memberDAO.selectOverlappedID(id);
	}
	
	@Override
	public String idFindSuccess(Map idByEmailMap) throws Exception {
		return memberDAO.idFindSuccess(idByEmailMap);
	}
	
	@Override
	public String idFindSuccessByNum(Map idByNumMap) throws Exception {
		return memberDAO.idFindSuccessByNum(idByNumMap);
	}
	
	@Override
	public String newPwdForm(Map findPwdMap) throws Exception {
		// String result="";
		 return memberDAO.newPwdForm(findPwdMap);
	}
	
	@Override
	public String pwdFindSuccess(Map pwdMap) throws Exception {
		String member_id = (String)pwdMap.get("member_id"); // �̸� ã��� ���̵�
		String newPwd = (String)pwdMap.get("newPwd");
		String salt = memberDAO.getSaltById(member_id);
		String password = newPwd;
		password  = SHA256Util.getEncrypt(password, salt);
		pwdMap.put("newPwd", password);
		String member_name= memberDAO.findMemberName(member_id);
	
		int update = memberDAO.pwdFindSuccess(pwdMap); 
	 
		return member_name; 
	}

	
	//access_token ��û
	@Override
	public String getAccessToken (String authorize_code) {
	String access_Token = "";
	String refresh_Token = "";
	String reqURL = "https://kauth.kakao.com/oauth/token";
	try {
	URL url = new URL(reqURL);
	 
	HttpURLConnection conn = (HttpURLConnection) 
	url.openConnection();
	// POST ��û�� ���� �⺻���� false�� setDoOutput�� true��
	 
	conn.setRequestMethod("POST");
	conn.setDoOutput(true);
	// POST ��û�� �ʿ�� �䱸�ϴ� �Ķ���� ��Ʈ���� ���� ����
	 
	BufferedWriter bw = new BufferedWriter(new 
	OutputStreamWriter(conn.getOutputStream()));
	StringBuilder sb = new StringBuilder();
	sb.append("grant_type=authorization_code");
	 
	sb.append("&client_id=b45cad6c7b351ab3b0f2ff42b3d5e362"); //������ �߱޹��� key
	sb.append("&redirect_uri=http://localhost:8080/gymbaro02/member/kakaoLogin"); // ������ ������ �ּ�
	 
	sb.append("&code=" + authorize_code);bw.write(sb.toString());
	bw.flush();
	 
	// ��� �ڵ尡 200�̶�� ����
	int responseCode = conn.getResponseCode();
	System.out.println("responseCode : " + responseCode);
	 
	// ��û�� ���� ���� JSONŸ���� Response �޼��� �о����
	BufferedReader br = new BufferedReader(new 
	InputStreamReader(conn.getInputStream()));
	String line = "";
	String result = "";
	 
	while ((line = br.readLine()) != null) {
	result += line;
	}
	System.out.println("response body : " + result);
	 
	// Gson ���̺귯���� ���Ե� Ŭ������ JSON�Ľ� ��ü ����
	JsonParser parser = new JsonParser();
	JsonElement element = parser.parse(result);
	 
	access_Token = 
	element.getAsJsonObject().get("access_token").getAsString();
	refresh_Token = 
	element.getAsJsonObject().get("refresh_token").getAsString();
	 
	System.out.println("access_token : " + access_Token);
	System.out.println("refresh_token : " + refresh_Token);
	 
	br.close();
	bw.close();
	} catch (IOException e) {
	e.printStackTrace();
	}
	return access_Token;
	}
	//����� ���� ��û
	@Override
	public HashMap<String, Object> getUserInfo(String access_Token) {
	// ��û�ϴ� Ŭ���̾�Ʈ���� ���� ������ �ٸ� �� �ֱ⿡ HashMapŸ������ ����
	HashMap<String, Object> userInfo = new HashMap<String, 
	Object>();
	String reqURL = "https://kapi.kakao.com/v2/user/me";
	try {URL url = new URL(reqURL);
	HttpURLConnection conn = (HttpURLConnection) 
	url.openConnection();
	conn.setRequestMethod("GET");
	// ��û�� �ʿ��� Header�� ���Ե� ����
	conn.setRequestProperty("Authorization", "Bearer " + 
	access_Token);
	int responseCode = conn.getResponseCode();
	System.out.println("responseCode : " + responseCode);
	BufferedReader br = new BufferedReader(new 
	InputStreamReader(conn.getInputStream()));
	String line = "";
	String result = "";
	while ((line = br.readLine()) != null) {
	result += line;
	}
	System.out.println("response body : " + result);
	JsonParser parser = new JsonParser();
	JsonElement element = parser.parse(result);
	JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
	String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	String email = kakao_account.getAsJsonObject().get("email").getAsString();
	userInfo.put("nickname", nickname);
	userInfo.put("email", email);
	} catch (IOException e) {
	e.printStackTrace();
	}
	return userInfo;
	}
	
	/* �޴��� ���� ���� �κ� */
	@Override
	public void certifiedPhoneNumber(String phoneNumber, String cerNum) {

	        String api_key = "NCSFYSP5ZGLEHJYA"; // api key
	        String api_secret = "SIDVIQGE0KCAAN0LYTXA6OQJXAM0FMH9"; // api secret
	        Message coolsms = new Message(api_key, api_secret);

	        // 4 params(to, from, type, text) are mandatory. must be filled
	        HashMap<String, String> params = new HashMap<String, String>();
	        params.put("to", phoneNumber);    // ������ȭ��ȣ
	        params.put("from", "01030279772");    // �߽���ȭ��ȣ. �׽�Ʈ�ÿ��� �߽�,���� �Ѵ� ���� ��ȣ�� �ϸ� ��
	        params.put("type", "SMS");
	        params.put("text", "[���ٷ�] ������ȣ " + cerNum + " �� �Է��ϼ���.");
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
