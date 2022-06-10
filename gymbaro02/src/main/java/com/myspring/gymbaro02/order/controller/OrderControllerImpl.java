package com.myspring.gymbaro02.order.controller;

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

import com.myspring.gymbaro02.cart.vo.CartVO;
import com.myspring.gymbaro02.member.vo.MemberVO;
import com.myspring.gymbaro02.order.service.OrderService;
import com.myspring.gymbaro02.order.vo.OrderVO;

@Controller("orderController")
@RequestMapping(value="/order")
public class OrderControllerImpl implements OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderVO orderVO;
	
	@Override
	@RequestMapping(value= "/order_02.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView order_02(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	@Override
	@RequestMapping(value="/orderEachGoods.do" , method = RequestMethod.POST)
	public ModelAndView orderEachGoods(@RequestParam Map<String,String> orderMap,
            HttpServletRequest request, HttpServletResponse response)  throws Exception {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		session=request.getSession();
		
		// ��ٱ��Ͽ��� �ֹ��ߴٸ�, īƮ���� ������ ������ �����س���
		if(orderMap.get("cart_id")!=null) {
			List<CartVO> deleteList = new ArrayList<CartVO>();
			CartVO cartVO = new CartVO();
			String cart_id = orderMap.get("cart_id");
			cartVO.setCart_id(Integer.parseInt(cart_id));
			deleteList.add(cartVO);
			session.setAttribute("deleteList", deleteList);
		}
		//
		
		// �޾ƿ� �� orderVO ��ü�� ����
		String goods_name = orderMap.get("goods_name");
		int goods_qty=0;
		int total_price=0;
		if(orderMap.get("goods_qty")!=null && orderMap.get("total_price")!=null) {
		goods_qty = Integer.parseInt(orderMap.get("goods_qty"));
		total_price = Integer.parseInt(orderMap.get("total_price"));
		}
		OrderVO _orderVO = new OrderVO();
		_orderVO.setGoods_id(Integer.parseInt(orderMap.get("goods_id")));
		_orderVO.setGoods_name(goods_name);
		_orderVO.setOption_name(orderMap.get("option_name"));
		_orderVO.setGoods_qty(goods_qty);
		_orderVO.setTotal_price(total_price);
		_orderVO.setFileName(orderMap.get("fileName"));
		session.setAttribute("orderInfo", _orderVO);
		session.setAttribute("orderMap", orderMap);
		
		System.out.println("qty:" + _orderVO.getGoods_qty() + ", total_price: " + _orderVO.getTotal_price());
		orderVO=_orderVO;

		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		List<OrderVO> myOrderList=new ArrayList<OrderVO>();
		myOrderList.add(orderVO);

		MemberVO memberInfo=(MemberVO)session.getAttribute("memberInfo");
		
		session.setAttribute("myOrderList", myOrderList);
		session.setAttribute("memberInfo", memberInfo);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/orderCheckCartGoods.do" ,method = RequestMethod.POST)
	public void orderCheckCartGoods(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		
		List<OrderVO> myOrderList=new ArrayList<OrderVO>();
		
		String[] goods_id = request.getParameterValues("goods_id");
		String[] goods_name = request.getParameterValues("goods_name");
		String[] goods_price = request.getParameterValues("goods_price");
		String[] option_name = request.getParameterValues("option_name");
		String[] goods_qty = request.getParameterValues("goods_qty");
		String[] fileName = request.getParameterValues("fileName");
		
		if(request.getParameterValues("cart_id")!=null) {
			// �ֹ� �� ��ٱ��� ��ǰ ������ ���� ������ ����
			String[] cart_id = request.getParameterValues("cart_id");
			List<CartVO> deleteList = new ArrayList<CartVO>();
			for(int i=0; i<cart_id.length; i++) {
				CartVO cartVO = new CartVO();
				cartVO.setCart_id(Integer.parseInt(cart_id[i]));
				deleteList.add(cartVO);
			}		
			session.setAttribute("deleteList", deleteList); // �ֹ� ������ ��ٱ��� ��ǰ �������ֱ� ���� �ֹ������� ������ ���ε�
		}
		
		
		int total_price = 0;
		int order_goods_qty = 0;
		for(int i=0; i<goods_id.length; i++) {
			OrderVO orderVO = new OrderVO();
			orderVO.setGoods_id(Integer.parseInt(goods_id[i]));
			orderVO.setGoods_name(goods_name[i]);
			orderVO.setTotal_price(Integer.parseInt(goods_price[i]));
			total_price += Integer.parseInt(goods_price[i]);
			orderVO.setOption_name(option_name[i]);
			orderVO.setGoods_qty(Integer.parseInt(goods_qty[i]));
			order_goods_qty += Integer.parseInt(goods_qty[i]);
			orderVO.setFileName(fileName[i]);
			myOrderList.add(orderVO);
		}
		OrderVO _orderVO = new OrderVO();
		_orderVO.setTotal_price(total_price);
		_orderVO.setGoods_qty(order_goods_qty);
		session.setAttribute("orderInfo", _orderVO);
		session.setAttribute("myOrderList", myOrderList);

	}
	
	@Override
	@RequestMapping(value= "/orderGoodsForm.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView orderGoodsform(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value= "/payToOrderGoods.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView payToOrderGoods(@RequestParam Map<String, String> receiverMap, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		session=request.getSession();
		int uid=0;
		String orderer_name="";
		
		if(session.getAttribute("memberInfo")!=null) {
			MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");
			uid = memberVO.getUid();
			orderer_name=memberVO.getMember_name(); // ȸ���̸� �ֹ��ڸ��� ȸ�� �̸����� �� ����
			
			// ����Ʈ ������� �� �����ϴ� �Լ� ����
			if(receiverMap.get("point")!="0") {
				String usePoint = receiverMap.get("point");
				Map<String,Object> pointMap = new HashMap<String, Object>();
				pointMap.put("uid", uid);
				pointMap.put("usePoint", usePoint);
				orderService.minusUsePoint(pointMap);
			}
		}else {
			orderer_name=receiverMap.get("orderer_name"); // ��ȸ���̸� �ֹ��ڴ� ����ڰ� �Է��� �����ͷ� �� ����
		}

		List<OrderVO> myOrderList = (List<OrderVO>)session.getAttribute("myOrderList");
		System.out.println("myOrderList.size:" + myOrderList.size());
		for(int i=0; i<myOrderList.size();i++){
			OrderVO orderVO=(OrderVO)myOrderList.get(i);
			orderVO.setOrderer_name(orderer_name);
			
			if(session.getAttribute("memberInfo")!=null) {
				orderVO.setUid(uid);
				orderVO.setMember_yn("Y");
			} else {
				orderVO.setMember_yn("N");
			}

			//orderVO.setGoods_qty(Integer.parseInt(receiverMap.get("goods_qty")));
			orderVO.setReceiver_name(receiverMap.get("receiver_name"));			
			orderVO.setReceiver_phone_number(receiverMap.get("receiver_phone_number"));
			orderVO.setReceiver_zipcode(receiverMap.get("receiver_zipcode"));
			orderVO.setReceiver_roadAddress(receiverMap.get("receiver_roadAddress"));
			orderVO.setReceiver_extraAddress(receiverMap.get("receiver_extraAddress"));
			orderVO.setPayment(receiverMap.get("payment"));
			orderVO.setTotal_price(Integer.parseInt(receiverMap.get("total_price")));
			orderVO.setDelivery_memo(receiverMap.get("delivery_memo"));
			
			myOrderList.set(i, orderVO); //�� orderVO�� �ֹ��� ������ ������ �� �ٽ� myOrderList�� �����Ѵ�.
		}//end for
		
	    String order_id = orderService.addNewOrder(myOrderList);
	    
	    // ȸ���� ��ȸ���� ��ٱ��Ͽ��� �ֹ��� �� ��� �ֹ� ���� �� ��ٱ��Ͽ��� �ش� ��ǰ�� �����ϱ�
	    if(session.getAttribute("deleteList")!=null) {
	    	List<CartVO> deleteList = (List<CartVO>)session.getAttribute("deleteList");
	    	orderService.deleteCartItem(deleteList); // ȸ���� db���� ������ ����
	    }
	    if(session.getAttribute("non_cartMap")!=null) {
	    	System.out.println("���� ������ ..? for�� ����");
	    	Map<String,List> non_cartMap = (Map<String, List>) session.getAttribute("non_cartMap");
	    	List<CartVO> myCartList = non_cartMap.get("myCartList");
	    	for(int i=0; i<myOrderList.size(); i++) {
	    		OrderVO _orderVO = myOrderList.get(i);
	    		System.out.println("orderVO:" + _orderVO.getOption_name());
	    		for(int j=0; j<myCartList.size(); j++) {
	    			CartVO cartVO = myCartList.get(j);
	    			System.out.println("cartVO:" + cartVO.getOption_name());
	    			if(cartVO.getOption_name().equals(_orderVO.getOption_name())) {
	    				System.out.println("���� ������ �ɼǸ�:" + cartVO.getOption_name() + ", " + _orderVO.getOption_name());
	    				if(i!=0 && j!=0) {
	    					myCartList.remove(j-1); // �������� ������ ��, �ϳ��� remove �� ������ ����Ʈ�� �ε����� ������ �ǹǷ�, ������ �����Ͱ� �ΰ� �̻��̶�� �ε���-1�� ���ش�.
						}else {
							myCartList.remove(j); // �ϳ��� �����Ѵٸ� ������ �ε��� ���� �״�� remove ���ش�.
						}
	    			}
	    		}
	    		non_cartMap.put("myCartList", myCartList);
	    	}
	    	session.removeAttribute("non_cartMap");
	    	session.setAttribute("non_cartMap", non_cartMap);
	    }
	    //
	    
	    receiverMap.put("order_id", order_id);
		mav.addObject("myOrderInfo",receiverMap);//OrderVO�� �ֹ���� ��������  �ֹ��� ������ ǥ���Ѵ�.
		mav.addObject("myOrderList", myOrderList);
		
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	@Override
	@RequestMapping("/order_coupon.do")
	public ModelAndView order_coupon(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		return mav;
	}
	
}
