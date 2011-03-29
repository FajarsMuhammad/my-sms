package com.sms.controller.master;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;

import com.sms.bean.master.Inbox;
import com.sms.common.util.ContexHelper;
import com.sms.service.master.InboxService;

public class InboxController extends GenericForwardComposer {

	/**
	 * Fajar Podolski
	 */
	private static final long serialVersionUID = 2987372181757715044L;

	//private Window winInbox;
	private Textbox txtInbox;
	private Listbox listBoxInbox;
	private Listheader inboxGroupHeader;
	private Listheader inboxDestinationHeader;
	private Listheader inboxSubjectHeader;
	private Paging paging;
	
	private static final InboxService inboxService = (InboxService) ContexHelper
			.getBean("inboxService");

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		generateListbox();
	}
	
	public void generateListbox() {

		List<Inbox> inboxs = new ArrayList<Inbox>();
		inboxs = inboxService.getListInbox();
		
		listBoxInbox.setModel(new ListModelList(inboxs));
		listBoxInbox.setItemRenderer(new InboxListModelItemRenderer());

	}
	
	public void onClick(Event event){
		Listitem item = listBoxInbox.getSelectedItem();
		Inbox inbox = (Inbox)item.getAttribute("data");
		txtInbox.setText(inbox.getSubject());
	}

	

}
