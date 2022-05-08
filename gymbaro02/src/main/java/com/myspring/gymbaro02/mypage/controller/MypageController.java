package com.myspring.gymbaro02.mypage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface MypageController {

	public ModelAndView myPage(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView listMyOrderHistory(@RequestParam Map<String, String> dateMap,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView listMyPointHistory(@RequestParam Map<String, String> dateMap,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView myPage04(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView myPage05(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView myPage06(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView outMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity modifyMyInfo(@RequestParam("attribute")  String attribute,
			@RequestParam("value")  String value, HttpServletRequest request, HttpServletResponse response)  throws Exception;
	
}
