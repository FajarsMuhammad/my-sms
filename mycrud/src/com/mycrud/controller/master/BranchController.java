package com.mycrud.controller.master;

import java.awt.Window;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;

import com.mycrud.bean.master.Branch;
import com.mycrud.common.util.ContexHelper;
import com.mycrud.service.master.BranchService;

public class BranchController extends GenericForwardComposer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8868294373411498511L;
		 
	private static final BranchService branchService = (BranchService) ContexHelper.getContext().getBean("branchService");

	private Window winBranchInput;
	private Textbox txtBranchCode;
	private Textbox txtBranchName;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}
	
	private Branch getBranchObject(){
		Branch branch = new Branch();
		branch.setId(txtBranchCode.getValue());
		branch.setBranchName(txtBranchName.getValue());
		return branch;
	}
	
	private void doSave(){
		Branch branch = getBranchObject();
		branchService.save(branch);
	}
	
	public void onClick$btnSubmit(Event e){
		alert("buzz");
		doSave();
	}

}
