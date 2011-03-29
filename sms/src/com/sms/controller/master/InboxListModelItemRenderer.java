package com.sms.controller.master;

import org.zkoss.zk.ui.sys.ComponentsCtrl;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.sms.bean.master.Inbox;

public class InboxListModelItemRenderer implements ListitemRenderer{

	@Override
	public void render(Listitem item, Object data) throws Exception {
		Inbox inbox = (Inbox) data;
		
		Listcell lc = new Listcell("Flag");
		lc.setParent(item);
		
		 lc = new Listcell(inbox.getGroup());
		lc.setParent(item);
		
		lc = new Listcell(inbox.getDestination());
		lc.setParent(item);
		
		lc = new Listcell(inbox.getSubject());
		lc.setParent(item);
		
		item.setAttribute("data", data);
		
		ComponentsCtrl.applyForward(item, "onClick=onClick");
		
	}
	
	

}
