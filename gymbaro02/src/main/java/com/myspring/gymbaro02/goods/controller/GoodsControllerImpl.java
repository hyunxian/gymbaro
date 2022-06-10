package com.myspring.gymbaro02.goods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.gymbaro02.goods.service.GoodsService;
import com.myspring.gymbaro02.goods.vo.GoodsVO;

@Controller("goodsController")
@RequestMapping(value="/goods")
public class GoodsControllerImpl implements GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@Override
	@RequestMapping(value= "/searchGoods.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchGoods(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		session = request.getSession();
		session.setAttribute("section", 1);
		session.setAttribute("pageNum", 1);
		ModelAndView mav=new ModelAndView();
		Map<String,List<GoodsVO>> goodsMap = goodsService.listGoods();
		
		session.setAttribute("goodsMap", goodsMap);
		//session.setAttribute("all", goodsMap.get("all"));
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	@Override
	@RequestMapping(value= "/goodsInfo.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		session = request.getSession();
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		
		Map goodsMap = goodsService.goodsDetail(goods_id);
		GoodsVO goodsVO = (GoodsVO) goodsMap.get("goodsVO");
		addGoodsInQuick(goods_id,goodsVO,session); //��ȸ�� ��ǰ������ ���޴��� ǥ���ϱ� ���� ���� 
		
		session.setAttribute("goodsVO", goodsVO);
		session.setAttribute("optionList", goodsMap.get("optionList"));
		session.setAttribute("imageList", goodsMap.get("imageList"));

		mav.setViewName(viewName);

		return mav;
	}
	
	private void addGoodsInQuick(String goods_id,GoodsVO goodsVO, HttpSession session) {
		boolean already_existed = false;
		List<GoodsVO> quickGoodsList;
		quickGoodsList = (ArrayList<GoodsVO>)session.getAttribute("quickGoodsList");
		//���ǿ� ����� ���� �ֱ� �� ��ǰ ����� ������ 
		if(quickGoodsList != null) { //�ֱ� �� ��ǰ�� �ִ� ��� 
			if(quickGoodsList.size() < 10) { //��ǰ ����� 4�� ������ ��� 
				for(int i =0; i<quickGoodsList.size(); i++) {
					GoodsVO goodsBean = (GoodsVO)quickGoodsList.get(i);
					if(goods_id.equals(goodsBean.getGoods_id())) {
						already_existed = true;
						break;
					}
				}//��ǰ���� ������ �̹� �����ϴ� ��ǰ���� �� 
				if(already_existed == false) {
					quickGoodsList.add(goodsVO);
				}//false�� ��ǰ������ ��Ͽ� ���� 
			}
		}else {
			quickGoodsList = new ArrayList<GoodsVO>();
			quickGoodsList.add(goodsVO);
			//�ֱ� �� ��ǰ����� ������ �����Ͽ� ��ǰ ������ ���� 
		}
		session.setAttribute("quickGoodsList", quickGoodsList); //��ǰ��� ���ǿ� ���� 
		session.setAttribute("quickGoodsListNum", quickGoodsList.size()); //��ǰ���� ���ǿ� ���� 
	}
}
