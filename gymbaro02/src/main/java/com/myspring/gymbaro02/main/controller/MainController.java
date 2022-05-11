package com.myspring.gymbaro02.main.controller;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.gymbaro02.common.GPS.GpsToAddress;

@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController {

	@RequestMapping(value= "/main/main.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		session = request.getSession();
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		return mav;
	}
	
	// �信�� �޾ƿ� ���� �浵 ���� �ּҷ� ��ȯ���ִ� �Լ��� ȣ���ؼ� �ּҸ� ���ǿ� ���ε� �����ִ� �Լ�
	@RequestMapping(value= "/main/ipCheck.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView ipCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		session = request.getSession();
		ModelAndView mav=new ModelAndView();

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
    	}
		
		mav.setViewName("/main/main");
		
		return mav;
	}
}
