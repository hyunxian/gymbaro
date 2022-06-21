package com.myspring.gymbaro02.cs.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface CsController {
	
	public ModelAndView csQnA(HttpServletRequest request, HttpServletResponse response) throws Exception; //���Ǳ� ��� ��ȸ 
	public ModelAndView personalQ(HttpServletRequest request, HttpServletResponse response) throws Exception; //���Ǳ� �����
	public ModelAndView addNewcs(@RequestParam Map<String,Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception; //���Ǳ� �߰�
	public ModelAndView csDetail(@RequestParam("csNO") String csNO, HttpServletRequest request, HttpServletResponse response) throws Exception; //���Ǳ� �� 
	public ModelAndView updateCS(@RequestParam Map<String,Object> CsMap, String csNO, HttpServletRequest request, HttpServletResponse response) throws Exception; //���Ǳ� ����
	public ModelAndView updateForm(@RequestParam("csNO")  String csNO, HttpServletRequest request, HttpServletResponse response) throws Exception; //���Ǳ� �ۼ�
	public ModelAndView deleteCS(@RequestParam("csNO") String csNO, HttpServletRequest request, HttpServletResponse response) throws Exception; //���Ǳ� ����
	public ModelAndView insertCsComment(@RequestParam String csNO, HttpServletRequest request, HttpServletResponse response) throws Exception; //�亯 �ۼ� 
	public ModelAndView updateCsComment(@RequestParam Map<String,Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception; //�亯 ���� 
	public ModelAndView updateCsForm(@RequestParam("csNO") String csNO, HttpServletRequest request, HttpServletResponse response) throws Exception; //�亯 ���� �� 
	public ModelAndView csCommentDelete(@RequestParam("cs_commentNO") String cs_commentNO, HttpServletRequest request, HttpServletResponse response) throws Exception; //�亯 ����
	
}
