package com.mycrud.controller;

import org.zkoss.zul.Window;

public abstract class AbstractMainWindowCreate extends AbstractMainWindow{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Window winCreate = null;
	private Window winConfirm = null;


	private String confirmComposer;
	
	
	public AbstractMainWindowCreate(){
		super();
		setConfirmComposer(initiateConfirmComposer());
	}
	protected abstract String initiateConfirmComposer();
	
	
	public void setWinCreate(
			Window winCreate) {
		this.winCreate = winCreate;
	}
	public Window getWinCreate() {
		return winCreate;
	}


	public void setWinConfirm(
			Window winConfirm) {
		this.winConfirm = winConfirm;
	}


	public Window getWinConfirm() {
		return winConfirm;
	}

	
	public void setConfirmComposer(String confirmComposer) {
		this.confirmComposer = confirmComposer;
	}
	public String getConfirmComposer() {
		return confirmComposer;
	}
	
	

}
