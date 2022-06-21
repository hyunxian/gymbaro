package com.myspring.gymbaro02.main.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.gymbaro02.common.GPS.GpsToAddress;
import com.myspring.gymbaro02.goods.service.GoodsService;
import com.myspring.gymbaro02.goods.vo.GoodsVO;
import com.myspring.gymbaro02.gym.controller.GymControllerImpl;
import com.myspring.gymbaro02.gym.service.GymService;
import com.myspring.gymbaro02.gym.vo.GymVO;

@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController {
	@Autowired
	private GymService gymService;
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value= "/main/main.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		session = request.getSession();
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		GymControllerImpl gymController = new GymControllerImpl();
		Map<String,List<GymVO>> gymMap = new HashMap<String,List<GymVO>>();
		
		// �ü� ��� ��ȸ
		Map<String, Object> _condMap = new HashMap<String, Object>();
		_condMap.put("limit", 9);
		gymMap = gymService.listGym(_condMap);
		List<GymVO> gymLocationList = gymMap.get("locationList");

		if(session.getAttribute("address") != "N/A" && session.getAttribute("address") != null) {
			String user_address = (String) session.getAttribute("address");
			for(int i=0; i < gymLocationList.size(); i++) {
				GymVO gymVO = gymLocationList.get(i);
				String gym_address = gymVO.getExtraAddress();
				String distance = gymController.checkDistance(gym_address,user_address,request);
				gymVO.setDistance(Double.parseDouble(distance));
			}
			// ����Ʈ �Ÿ������� �������� �����ϱ�
			gymLocationList = gymLocationList.stream().sorted(Comparator.comparing(GymVO::getDistance)).collect(Collectors.toList());
			gymMap.put("locationList", gymLocationList);
		}else {
			session.setAttribute("addressCheck", "N");
		}
		
		Map<String,Object> condMap = new HashMap<String, Object>();
		condMap.put("limit", 100);
		condMap.put("order_item", "");
		Map<String,List<GoodsVO>> goodsMap = goodsService.listGoods(condMap);
		
		session.setAttribute("goodsMap", goodsMap);		
		session.setAttribute("gymMap", gymMap);
		
		mav.setViewName(viewName);
		
		return mav;
	}
	
	// �信�� �޾ƿ� ���� �浵 ���� �ּҷ� ��ȯ���ִ� �Լ��� ȣ���ؼ� �ּҸ� ���ǿ� ���ε� �����ִ� �Լ�
	@RequestMapping(value= "/main/ipCheck.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public void ipCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		session = request.getSession();

		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		session.setAttribute("latitude", latitude); // ������ �����ͷ� �� ������ ���ε�
		session.setAttribute("longitude", longitude); // ������ �����ͷ� �� �浵�� ���ε�
		if(!latitude.equals("N/A")) {
			Double latitude2 = Double.parseDouble(latitude);
			Double longitude2 = Double.parseDouble(longitude);
			GpsToAddress address = new GpsToAddress(latitude2, longitude2); // �ּ� ��ȯ �Լ�
			System.out.println("address:" + address.getAddress()); // �ּ�Ȯ��
			session.setAttribute("address", address.getAddress()); // ���� ���ε�
			session.setAttribute("addressCheck", "Y");
		}else {
			session.setAttribute("address", "N/A");
		}
			
	}
}
