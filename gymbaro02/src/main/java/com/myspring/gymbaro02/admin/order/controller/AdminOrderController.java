package com.myspring.gymbaro02.admin.order.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface AdminOrderController {
	public ModelAndView listOrder(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
