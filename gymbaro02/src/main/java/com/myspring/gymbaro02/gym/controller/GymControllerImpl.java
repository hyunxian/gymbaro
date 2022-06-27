package com.myspring.gymbaro02.gym.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
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

import com.myspring.gymbaro02.config.ApiKey;
import com.myspring.gymbaro02.gym.service.GymService;
import com.myspring.gymbaro02.gym.vo.GymReviewVO;
import com.myspring.gymbaro02.gym.vo.GymVO;
import com.myspring.gymbaro02.member.vo.MemberVO;
import com.myspring.gymbaro02.membership.vo.MembershipVO;

@Controller("gymController")
@RequestMapping(value="/gym")
public class GymControllerImpl implements GymController {
	@Autowired
	private GymService gymService;
	@Inject
	private ApiKey apiKey;

	private final String KEY = apiKey.getGoogleMap();
	private final String KAKAOMAP_KEY = apiKey.getKakaoMap();
	
	@RequestMapping(value= "/searchGyms.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchGym(@RequestParam(value="limit", defaultValue="12") String limit, @RequestParam Map<String,Object> condMap, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		session = request.getSession();
		ModelAndView mav=new ModelAndView();
		Map<String,List<GymVO>> gymMap = new HashMap<String,List<GymVO>>();
		List<GymVO> gymLocationList = new ArrayList<GymVO>();

		if(request.getParameter("update_address") != null) { // ���� �ּҸ� �������� �����ߴٸ� address ������ ����� �ּҰ��� ������
			String update_address = request.getParameter("update_address");
			session.removeAttribute("address");
			session.setAttribute("address", update_address); // ����� �ּҷ� �ٽ� ���ǿ� ���ε�
		}
		
		if(request.getParameter("keyword") != null) {
			String keyword = request.getParameter("keyword");
			condMap.put("keyword", keyword);
		}
		
		condMap.put("limit", Integer.parseInt(limit));
			
		gymMap = gymService.listGym(condMap);
		gymLocationList = gymMap.get("locationList");
		
		if(session.getAttribute("address") != "N/A" && session.getAttribute("address") != null) { // address�� �ΰ��� �ƴϰ� ��ġ���Ǹ� �ߴٸ�
			String user_address = (String) session.getAttribute("address");
			for(int i=0; i < gymLocationList.size(); i++) {
				GymVO gymVO = gymLocationList.get(i);
				String gym_address = gymVO.getExtraAddress();
				String distance = checkDistance(gym_address, user_address, request);
				gymVO.setDistance(Double.parseDouble(distance));
			}
			// ����Ʈ �Ÿ������� �������� �����ϱ�
			gymLocationList = gymLocationList.stream().sorted(Comparator.comparing(GymVO::getDistance)).collect(Collectors.toList());
		}

		gymMap.put("locationList", gymLocationList);
		
		session.setAttribute("gymMap", gymMap);
		session.setAttribute("condMap", condMap);
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	public String checkDistance(String gym_address, String my_address, HttpServletRequest request) throws Exception {
		String distance="";
		
		//�ü� �ּ� �� ���� �浵�� ��ȯ
		String _address = URLEncoder.encode(gym_address, "UTF-8");
		String apiURL = "https://maps.googleapis.com/maps/api/geocode/json?sensor=false&language=ko&address="+_address+"&key="+KEY;
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
		
		// ����� �ּ� �� ���� �浵�� ��ȯ
		String user_address = URLEncoder.encode(my_address, "UTF-8");
		String user_apiURL = "https://maps.googleapis.com/maps/api/geocode/json?sensor=false&language=ko&address="+user_address+"&key="+KEY;
		String user_jsonString = new String();
		String user_buf;
		URL user_url = new URL(user_apiURL);
		URLConnection user_conn = user_url.openConnection();
		BufferedReader user_br = new BufferedReader(new InputStreamReader(
				user_conn.getInputStream(), "UTF-8"));
		while ((user_buf = user_br.readLine()) != null) {
			user_jsonString += user_buf;
		}
		JSONObject user_jObj = (JSONObject) JSONValue.parse(user_jsonString);
		JSONArray user_jArray = (JSONArray) user_jObj.get("results");
		if( user_jArray.size()!=0){
			user_jObj = (JSONObject) user_jArray.get(0);
		}
		user_jObj = (JSONObject) user_jObj.get("geometry");
		user_jObj = (JSONObject) user_jObj.get("location");
		
		//���� ��ġ�� �ü� �ּ� �Ÿ� ���
		String apiURL2 ="https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&mode=transit&origins="+user_jObj.get("lat")+","+user_jObj.get("lng")+"&destinations="+jObj.get("lat")+","+jObj.get("lng")+"&region=KR&key="+KEY;
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
	
	// �������������� ���� �ۼ� ������ ����
		@RequestMapping(value="/newReviewForm.do", method={RequestMethod.POST,RequestMethod.GET})
		public ModelAndView newReviewForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
			HttpSession session = request.getSession();
			ModelAndView mav = new ModelAndView();
			
			MembershipVO reviewVO = (MembershipVO) session.getAttribute("membershipVO");
			
			int gym_id = reviewVO.getGym_id();
			mav.setViewName("redirect:/gym/gymsInfo.do?gym_id=" + gym_id);
			
			return mav;
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
			
			Map membershipInfoMap = new HashMap();
			membershipInfoMap.put("gym_id", gym_id);
			membershipInfoMap.put("uid", uid);
			Integer membershipInfo = gymService.getMembershipInfo(membershipInfoMap);
			mav.addObject("membershipInfo", membershipInfo);
		}
		
		// �α��� �� ���������� ��θ� ���ؼ��� ���� �ۼ��� �� �ְ� ��	
				if (session.getAttribute("memberInfo") != null) { // ��α��� ���� ����
					if(session.getAttribute("membershipVO") != null) { 
						MembershipVO reviewVO = (MembershipVO) session.getAttribute("membershipVO"); 
							String reviewState = reviewVO.getReview_yn();
							session.setAttribute("reviewState", reviewState);	
					  }
		}
		
				
		Map gymMap = gymService.GymDetail(gym_id, uid);
		session.setAttribute("gymMap", gymMap);
		
		List<GymReviewVO> viewAll = gymService.viewAll(gym_id); // ��� �ı� ������
		session.setAttribute("viewAll", viewAll);
		
		String reviewCount = gymService.getReviewCount(gym_id); // ��� �ı� ���� count
		
		// ���� ���ϱ�
		double score_gym = gymService.getGymAvg(gym_id); // �ü� ���� ���
		double score_instructor = gymService.getInstructorAvg(gym_id); // ���� ���� ���
		double score_access = gymService.getAccessAvg(gym_id); // ���ټ� ���� ���
		double total_avg = (score_gym + score_instructor + score_access)/3; // �ü� �� ���� ���
		String avg_result = String.format("%.2f", total_avg); // �� ���� ��� �Ҽ��� �Ʒ� �ι�° �ڸ����� ���
		
		// ���� ���� ��� ���ϱ�
		List<GymReviewVO> personalScoreList = (List<GymReviewVO>) session.getAttribute("viewAll");
		Map<String, Object> personalScoreMap = new HashMap();
			personalScoreMap.put("gym_id", gym_id);
		for(int i=0; i<personalScoreList.size(); i++) {
			String membership_id = personalScoreList.get(i).getMembership_id();		
			personalScoreMap.put("membership_id", membership_id);
		}
		double personalScore_gym = gymService.getPersonalScoreAvg_gym(personalScoreMap);
		double personalScore_instructor = gymService.getPersonalScoreAvg_instructor(personalScoreMap);
		double personalScore_access = gymService.getPersonalScoreAvg_access(personalScoreMap);
		double total_personal_avg = (personalScore_gym + personalScore_instructor + personalScore_access) /3;
		String personal_avg_result = String.format("%.2f", total_personal_avg);
		
		mav.addObject("gym_id", gym_id);
		mav.addObject("score_gym", score_gym);
		mav.addObject("score_instructor", score_instructor);
		mav.addObject("score_access", score_access);
		mav.addObject("avg_result", avg_result);
		mav.addObject("viewAll", viewAll);
		mav.addObject("reviewCount", reviewCount);
		mav.addObject("personal_avg_result", personal_avg_result);
		
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
		mav.addObject("KAKAOMAP_KEY", KAKAOMAP_KEY);

		return mav;
	}
	
	// ���� �ۼ� �� �����ͺ��̽��� ���
		@Override
		@RequestMapping(value= "/addNewReview.do", method={RequestMethod.POST,RequestMethod.GET})
		public ModelAndView newReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();
			ModelAndView mav = new ModelAndView();
			
			MembershipVO newReviewVO = (MembershipVO) session.getAttribute("membershipVO");
			
			GymReviewVO gymReviewVO = new GymReviewVO();
			int gym_id = Integer.parseInt(request.getParameter("gym_id"));
			MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
			int uid = memberVO.getUid();
			String review_writer = memberVO.getMember_name();
			String membership_id = newReviewVO.getMembership_id();
			
			String content = request.getParameter("content");
			int score_gym = Integer.parseInt(request.getParameter("score_gym"));
			int score_instructor = Integer.parseInt(request.getParameter("score_instructor"));
			int score_access = Integer.parseInt(request.getParameter("score_access"));

			gymReviewVO.setUid(uid);
			gymReviewVO.setGym_id(gym_id);
			gymReviewVO.setReview_writer(review_writer);
			gymReviewVO.setContent(content);
			gymReviewVO.setScore_gym(score_gym);
			gymReviewVO.setScore_instructor(score_instructor);
			gymReviewVO.setScore_access(score_access);
			gymReviewVO.setMembership_id(membership_id);
			
			gymService.newReview(gymReviewVO);
			
			Map<String, Object> updateReviewMap = new HashMap();
			updateReviewMap.put("gym_id", gym_id);
			updateReviewMap.put("membership_id", membership_id);
			gymService.updateReviewState(updateReviewMap); // 
			
			mav.addObject("gymReviewVO", gymReviewVO);
			mav.setViewName("redirect:/gym/gymsInfo.do?gym_id=" + gym_id);
			
			return mav;
		}

		@Override
		@RequestMapping(value= "/deleteGymReview.do", method={RequestMethod.POST,RequestMethod.GET})
		public ModelAndView deleteGymReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			HttpSession session = request.getSession();
		
			MembershipVO deleteReviewVO = (MembershipVO) session.getAttribute("membershipVO");
			
			int gym_id = deleteReviewVO.getGym_id();
				
			String membership_id = deleteReviewVO.getMembership_id();
			
			Map<String, Object> deleteReviewMap = new HashMap();
			deleteReviewMap.put("gym_id", gym_id);
			deleteReviewMap.put("membership_id", membership_id);
			
			gymService.deleteGymReview(deleteReviewMap);
			
			mav.setViewName("redirect:/gym/gymsInfo.do?gym_id=" + gym_id);
			return mav;
		}
}
