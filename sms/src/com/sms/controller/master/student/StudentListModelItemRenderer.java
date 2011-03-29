package com.sms.controller.master.student;

import org.zkoss.zk.ui.sys.ComponentsCtrl;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Space;

import com.sms.bean.master.Student;

public class StudentListModelItemRenderer implements ListitemRenderer {

	@Override
	public void render(Listitem item, Object data) throws Exception {
		Student siswa = (Student) data;
		
		Listcell lc = new Listcell(siswa.getId()+"");
		lc.setParent(item);
		
		lc = new Listcell(siswa.getName());
		lc.setParent(item);
		
		lc = new Listcell(siswa.getAddress());
		lc.setParent(item);
		
		Div div = new Div();
		A edit = new A("Edit");
		edit.setParent(div);
		
		div.appendChild(new Space());
		
		A delete = new A("Delete");
		delete.setParent(div);
		
		lc = new Listcell();
		div.setParent(lc);
		lc.setParent(item);
		
		item.setAttribute("data", data);
		edit.setAttribute("editSiswa", siswa.getId());
		
		ComponentsCtrl.applyForward(item, "onClick=onClick");
		ComponentsCtrl.applyForward(edit, "onClick=onClickEdit");
		
	}
	
	

}
