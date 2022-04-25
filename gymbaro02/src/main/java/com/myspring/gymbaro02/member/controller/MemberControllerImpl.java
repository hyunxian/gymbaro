package com.myspring.gymbaro02.member.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.gymbaro02.member.service.MemberService;
import com.myspring.gymbaro02.member.vo.MemberVO;

@Controller("memberController")
@RequestMapping(value="/member")
public class MemberControllerImpl implements MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;
	
	@RequestMapping(value= "/loginForm.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	@RequestMapping(value= "/idpwdFindForm.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView idpwdFind(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	@RequestMapping(value= "/idFindSuccess.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView idFindSuccess(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	@RequestMapping(value= "/newPwdForm.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView newPwdForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	@RequestMapping(value= "/pwdFindSuccess.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView pwdFindSuccess(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	@RequestMapping(value= "/join01.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView join01(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	@RequestMapping(value= "/join02.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView join02(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		session = request.getSession();
		String join_type = request.getParameter("join_type");
		session.setAttribute("join_type", join_type);
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	@RequestMapping(value= "/join03_1.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView join03_1(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	@RequestMapping(value= "/join03_2.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView join03_2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	@RequestMapping(value= "/join04.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView join04(@RequestParam("hp") String hp, @RequestParam("email1") String email1, @RequestParam("email2") String email2, 
			@RequestParam("join_type") String join_type, @ModelAttribute("memberVO") MemberVO _memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		session = request.getSession();
		ModelAndView mav=new ModelAndView();
		
		// member_type ����
		if(join_type.equals("common")) {
			_memberVO.setMember_type("�Ϲ�");
		} else if(join_type.equals("gym")) {
			_memberVO.setMember_type("�ü�");
		}
		
		// �޴�����ȣ ������ �߰�
		String phone_number = phoneNumberHyphenAdd(hp);
		_memberVO.setPhone_number(phone_number);
		
		// �̸����� �� ���� �ƴ϶�� if��
		if(!(email1.equals("")) && !(email2.equals(""))) {
			String email = email1 + "@" + email2; // �޾ƿ� �̸��� �� ����
			_memberVO.setEmail(email); 
		}
		
		memberService.addMember(_memberVO);
		
		// ���� value �� ���� ���� �߰�
		String member_id = _memberVO.getMember_id();
		String member_name = _memberVO.getMember_name();
		
		// ȸ������ ���� ȭ�鿡 ���̵�, �̸� �����ֱ�
		session.setAttribute("member_id", member_id);
		session.setAttribute("member_name", member_name);
		
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	@Override
	@RequestMapping(value="/overlapped.do" ,method = RequestMethod.POST)
	public ResponseEntity overlapped(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResponseEntity resEntity = null;
		String result = memberService.overlapped(id);
		resEntity =new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
	}
	
	/* �޴��� ���� ��Ʈ�ѷ� */
	@RequestMapping("/check/sendSMS")
    public @ResponseBody String sendSMS(String phoneNumber) {

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        System.out.println("������ ��ȣ : " + phoneNumber);
        System.out.println("������ȣ : " + numStr);
        memberService.certifiedPhoneNumber(phoneNumber,numStr);
        return numStr;
    }
	
	// �޴��� ��ȣ ������ �߰� �Լ�
	public static String phoneNumberHyphenAdd(String num) {

	    String formatNum = "";
	    num = num.replaceAll("-","");
	    
	    if (num.length() == 11) {
	        formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
	    }
	    /* else if(num.length()==8){
	        formatNum = num.replaceAll("(\\d{4})(\\d{4})", "$1-$2");
	    } */
	    
	    return formatNum;
	}
}
