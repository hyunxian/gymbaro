package com.myspring.gymbaro02.admin.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.gymbaro02.admin.event.dao.AdminEventDAO;
import com.myspring.gymbaro02.event.vo.EventVO;

@Service("adminEventService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminEventServiceImpl implements AdminEventService {
	@Autowired
	private AdminEventDAO adminEventDAO;
	
	// �̺�Ʈ ����Ʈ ��ȸ
	@Override
	public List<EventVO> listEvent() throws Exception {
		List<EventVO> listEvent = adminEventDAO.selectEventList();
		return listEvent;
	}
	
	// �� �̺�Ʈ �߰��ϱ�
	@Override
	public void addNewEvent(EventVO eventVO) throws Exception{
		adminEventDAO.addNewEvent(eventVO);
	}
	
	// �̺�Ʈ �����ϱ�(�������� ����Ǿ��ٸ�, �ش� �̺�Ʈ�� ����� ��ǰ�� �������� ��������ش�)
	@Override
	public void updateEvent(EventVO eventVO, String discount_update) throws Exception {
		adminEventDAO.updateEvent(eventVO);
		if(discount_update.equals("Y")) {
			int event_id = eventVO.getEvent_id();
			adminEventDAO.updateThisGoods(event_id);
		}
	}
	
	// �̺�Ʈ �����ϱ�
	@Override
	public void deleteEvent(List<EventVO> eventList) throws Exception {
		adminEventDAO.deleteGoodsDiscount(eventList); // �̺�Ʈ ������ �̺�Ʈ ���̵� ����Ű�� ����� ��ǰ�� �̺�Ʈ�� ���������ش�
		adminEventDAO.deleteEvent(eventList); // ��ǰ �̺�Ʈ ���� ���� ��, �̺�Ʈ�� ���̺��� �����Ѵ�
	}
	
}
