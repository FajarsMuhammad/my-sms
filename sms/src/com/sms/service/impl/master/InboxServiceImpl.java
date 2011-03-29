package com.sms.service.impl.master;

import java.util.List;

import com.sms.bean.master.Inbox;
import com.sms.dao.master.InboxDAO;
import com.sms.service.master.InboxService;

public class InboxServiceImpl implements InboxService{
	
	private InboxDAO inboxDAO;

	public InboxDAO getInboxDAO() {
		return inboxDAO;
	}

	public void setInboxDAO(InboxDAO inboxDAO) {
		this.inboxDAO = inboxDAO;
	}

	public InboxServiceImpl() {
		
	}

	public List<Inbox> getListInbox() {
		return getInboxDAO().getListInbox();
	}

}
