package com.mycrud.controller;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

public abstract class AbstractMainWindow extends Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String createZul;

	public AbstractMainWindow(){
		setCreateZul(initiateCreateZul());
	}
	protected abstract String initiateCreateZul();
	
	public void onCreate(){
		System.out.println("onCreate AbstractMainWindow called");
		Executions.createComponents(this.getCreateZul(), this, getArgs());
	}
	public Map<String,Object> getArgs() {
		// TODO Auto-generated method stub
		return null;
	}
	public void loadPage(String uri){
		Executions.createComponents(uri, this, null);
	}
	public void setCreateZul(String createZul) {
		this.createZul = createZul;
	}
	public String getCreateZul() {
		return createZul;
	}
	
}
