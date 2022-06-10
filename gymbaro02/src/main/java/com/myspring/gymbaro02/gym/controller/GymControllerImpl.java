package com.myspring.gymbaro02.gym.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.gymbaro02.gym.service.GymService;
import com.myspring.gymbaro02.gym.vo.GymVO;
import com.myspring.gymbaro02.member.vo.MemberVO;

@Controller("gymController")
@RequestMapping(value="/gym")
public class GymControllerImpl implements GymController {
	@Autowired
	private GymService gymService;


	@RequestMapping(value= "/searchGyms.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchGym(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		session = request.getSession();
		ModelAndView mav=new ModelAndView();
		String _address = (String) session.getAttribute("address");
		Map<String,List<GymVO>> gymMap = gymService.listGym(_address);
		
		List<GymVO> gymLocationList = gymMap.get("locationList");
		if(!_address.equals("N/A") && _address != null) {
			for(int i=0; i < gymLocationList.size(); i++) {
				GymVO gymVO = gymLocationList.get(i);
				String address = gymVO.getExtraAddress();
				String distance = checkDistance(address,request);
				gymVO.setDistance(Double.parseDouble(distance));
			}
			// ����Ʈ �Ÿ������� �������� �����ϱ�
			gymLocationList = gymLocationList.stream().sorted(Comparator.comparing(GymVO::getDistance)).collect(Collectors.toList());
		}

		gymMap.put("locationList", gymLocationList);
		
		session.setAttribute("gymMap", gymMap);
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	public String checkDistance(String address, HttpServletRequest request) throws Exception {
		String distance="";
		
		//�ּ� �� ���� �浵�� ��ȯ
		String _address = URLEncoder.encode(address, "UTF-8");
		String apiURL = "https://maps.googleapis.com/maps/api/geocode/json?sensor=false&language=ko&address="+_address+"&key=AIzaSyCk-KTEGLfLGD3L7w5IdR8-kxKIhrjD-o8";
		String jsonString = new String();
		String buf;
		URL url = new URL(apiURL);
		URLConnection conn = url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "UTF-8"));
		while ((buf = br.readLine()) != null) {
			jsonString += buf;
		}
		JSONObject jObj = (JSONObject) JSONValue.parse(jsonString);
		JSONArray jArray = (JSONArray) jObj.get("results");
		if( jArray.size()!=0){
		jObj = (JSONObject) jArray.get(0);
		}
		jObj = (JSONObject) jObj.get("geometry");
		jObj = (JSONObject) jObj.get("location");
		//
		HttpSession session;
		session = request.getSession();
		
		//���� ��ġ�� �ü� �ּ� �Ÿ� ���
		String apiURL2 ="https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&mode=transit&origins="+session.getAttribute("latitude")+","+session.getAttribute("longitude")+"&destinations="+jObj.get("lat")+","+jObj.get("lng")+"&region=KR&key=AIzaSyCk-KTEGLfLGD3L7w5IdR8-kxKIhrjD-o8";
		String jsonString2 = new String();
		String buf2;
		URL url2 = new URL(apiURL2);
		URLConnection conn2 = url2.openConnection();
		BufferedReader br2 = new BufferedReader(new InputStreamReader(
				conn2.getInputStream(), "UTF-8"));
		while ((buf2 = br2.readLine()) != null) {
			jsonString2 += buf2;
		}
		JSONObject jObj2 = (JSONObject) JSONValue.parse(jsonString2);
		JSONArray jArray2 = (JSONArray) jObj2.get("rows");
		if( jArray2.size()!=0){ 
		jObj2 = (JSONObject) jArray2.get(0);
		}
		jArray2= (JSONArray) jObj2.get("elements");
		jObj2= (JSONObject) jArray2.get(0);
		jObj2 = (JSONObject) jObj2.get("distance");
		distance = jObj2.get("text").toString();
		String[] _distance = distance.split("\\s+");
		distance = _distance[0];
		//
		return distance;
	}
	
	@RequestMapping(value= "/gymsInfo.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView gymsInfo(@RequestParam("gym_id") String gym_id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		session = request.getSession();				
		ModelAndView mav=new ModelAndView();
		int uid = 0;
		if(session.getAttribute("memberInfo") != null) {
			MemberVO memberVO = (MemberVO)session.getAttribute("memberInfo");
			uid = memberVO.getUid();
		}
		Map gymMap = gymService.GymDetail(gym_id, uid);
		session.setAttribute("gymMap", gymMap);
		
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	
	@RequestMapping(value= "/searchMap.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchMap(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
}
