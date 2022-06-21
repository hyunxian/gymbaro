package com.myspring.gymbaro02.goods.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.myspring.gymbaro02.goods.vo.GoodsReviewVO;
import com.myspring.gymbaro02.goods.vo.GoodsVO;
import com.myspring.gymbaro02.member.vo.MemberVO;
import com.myspring.gymbaro02.order.vo.OrderVO;

@Controller("goodsController")
@RequestMapping(value="/goods")
public class GoodsControllerImpl implements GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@Override
	@RequestMapping(value= "/searchGoods.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchGoods(@RequestParam(value="limit", defaultValue="12") String limit, @RequestParam Map<String,Object> condMap, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		session = request.getSession();
		ModelAndView mav=new ModelAndView();
		List<String> subItemList = new ArrayList<String>();
		System.out.println("limit:" + limit);
		condMap.put("limit", Integer.parseInt(limit));
		if(request.getParameterValues("sub_item")!=null) {
			String[] sub_item = request.getParameterValues("sub_item");
			for(int i=0; i<sub_item.length; i++) {
				subItemList.add(sub_item[i]);
			}
			condMap.put("subItemList", subItemList);
		}

		Map<String,List<GoodsVO>> goodsMap = goodsService.listGoods(condMap);
		
		session.setAttribute("goodsMap", goodsMap);
		session.setAttribute("condMap", condMap);
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
		
		// �Ǹ޴� ����
		if(request.getParameter("tab")!=null) {
			String tab = request.getParameter("tab");
			mav.addObject("tab", tab);
		}
		
		
		Map goodsMap = goodsService.goodsDetail(goods_id);
		GoodsVO goodsVO = (GoodsVO) goodsMap.get("goodsVO");
		addGoodsInQuick(goods_id,goodsVO,session); //��ȸ�� ��ǰ������ ���޴��� ǥ���ϱ� ���� ���� 
		
		session.setAttribute("goodsVO", goodsVO);
		session.setAttribute("optionList", goodsMap.get("optionList"));
		session.setAttribute("imageList", goodsMap.get("imageList"));

		List<GoodsReviewVO> viewAll = goodsService.viewAll(goods_id); // ��ü ���� ��ȸ
		mav.addObject("viewAll", viewAll);
	
		if (viewAll != null) {
		int reviewCount = goodsService.getReviewCount(goods_id); // ���� ���� ī��Ʈ 
		int countScore1 = goodsService.countScore1(goods_id); // 1�� ���� ī��Ʈ
		int countScore2 = goodsService.countScore2(goods_id); // 2�� ���� ī��Ʈ
		int countScore3 = goodsService.countScore3(goods_id); // 3�� ���� ī��Ʈ 
		int countScore4 = goodsService.countScore4(goods_id); // 4�� ���� ī��Ʈ
		int countScore5 = goodsService.countScore5(goods_id); // 5�� ���� ī��Ʈ
		double scoreAvg = goodsService.getScoreAvg(goods_id); // ��� ���� ���
		
		// �α��� �� ���������� ��θ� ���ؼ��� ���� �ۼ��� �� �ְ� ��	
		if (session.getAttribute("memberInfo") != null) { // ��α��� ���� ����
			if(session.getAttribute("orderDetailList") != null) { // �α��� �� �ٷ� ��ǰ ������ ���� �� �ۼ� â ���� ����
				List<OrderVO> orderList = (List<OrderVO>) session.getAttribute("orderDetailList"); 
		
					for(int i=0; i<orderList.size(); i++) {
						String order_id = orderList.get(i).getOrder_id(); 
						String option_name = request.getParameter("option_name");	
						Map<String, Object> reviewInfoMap = new HashMap();
						reviewInfoMap.put("goods_id", goods_id);
						reviewInfoMap.put("order_id", order_id);
						reviewInfoMap.put("option_name", option_name);
			
						String reviewState = goodsService.getReviewInfo(reviewInfoMap); // order_id + goods_id�� �ش��ϴ� review_yn ���� ������ Y or N 
						
						session.setAttribute("reviewState", reviewState);
					}
				}
			}
		
		// �������������� ���� �Է½� �ٹ������ ���� �ֹ���ȣ �޾ƿ���
		if(request.getParameter("order_id")!=null && request.getParameter("option_name")!=null) {
			String order_id = request.getParameter("order_id");
			String option_name = request.getParameter("option_name");
			System.out.println("goodsInfo.do: " + order_id +"," + option_name);
			mav.addObject("myOrder_id", order_id);
			mav.addObject("myOption_name", option_name);
		}
		
		session.setAttribute("goodsCsList", goodsMap.get("goodsCsList"));
		session.setAttribute("commentList", goodsMap.get("commentList"));
		
		mav.addObject("reviewCount", reviewCount);
		mav.addObject("countScore1", countScore1);
		mav.addObject("countScore2", countScore2);
		mav.addObject("countScore3", countScore3);
		mav.addObject("countScore4", countScore4);
		mav.addObject("countScore5", countScore5);
		mav.addObject("scoreAvg", scoreAvg);
		mav.addObject("viewAll", viewAll);
		
		} else {
			mav.addObject("viewAll", viewAll);
		}
		
		mav.setViewName(viewName);

		return mav;
	}
	
	// �������������� ���� �ۼ� ������ ����
		@RequestMapping(value="/newReviewForm.do", method= {RequestMethod.POST, RequestMethod.GET})
		public ModelAndView newReviewForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();
			ModelAndView mav = new ModelAndView();
			String viewName = (String)request.getAttribute("viewName");
			
			List<OrderVO> orderList = (List<OrderVO>) session.getAttribute("orderDetailList"); // �ֹ� ������ ����ִ� ����Ʈ�� ���ǿ��� �޾ƿ�
			
			String goods_id = request.getParameter("goods_id");
			String order_id = request.getParameter("order_id");
			String option_name = request.getParameter("option_name");

			mav.setViewName("redirect:/goods/goodsInfo.do?goods_id=" + goods_id + "&order_id="+order_id+"&option_name="+option_name);
					
			return mav;
		}
		
		// ���� �ۼ� �� �����ͺ��̽��� ����
		@Override
		@RequestMapping(value="/addNewReview.do", method= {RequestMethod.POST, RequestMethod.GET})
		public ModelAndView addNewReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();
			ModelAndView mav = new ModelAndView();
			
			GoodsReviewVO goodsReviewVO = new GoodsReviewVO();
				
			MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
			int uid = memberVO.getUid();
			int member_level = memberVO.getMember_level();
			String review_writer = memberVO.getMember_name();
			
			String content = request.getParameter("content");
			int score = Integer.parseInt(request.getParameter("score"));
			
			String goods_id = request.getParameter("goods_id");
			String order_id = request.getParameter("order_id");
			String option_name = request.getParameter("option_name");
			
			Map<String, Object> updateReviewStateMap = new HashMap<String, Object>();
			updateReviewStateMap.put("goods_id", goods_id);
			updateReviewStateMap.put("order_id", order_id);
			updateReviewStateMap.put("option_name", option_name);
			
			goodsReviewVO.setGoods_id(goods_id);
			goodsReviewVO.setUid(uid);
			goodsReviewVO.setReview_writer(review_writer);
			goodsReviewVO.setContent(content);
			goodsReviewVO.setScore(score);
			goodsReviewVO.setMember_level(member_level);
			goodsReviewVO.setOrder_id(order_id);
			goodsReviewVO.setOption_name(option_name);
			
			
		
			goodsService.addNewReview(goodsReviewVO);
			goodsService.updateReviewState(updateReviewStateMap);
			
			session.setAttribute("goodsReviewVO", goodsReviewVO);
		
			mav.setViewName("redirect:goodsInfo.do?tab=review&goods_id=" + goods_id);
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
