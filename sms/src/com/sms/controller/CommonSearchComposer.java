package com.sms.controller;

import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.ListitemRenderer;

public abstract class CommonSearchComposer extends CommonComposer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Div divSearchResult;
	protected ListModelList modelList;
	protected Listbox lsSearchResult;
	
	protected abstract ListitemRenderer createListItemRenderer();
	protected abstract void search();
	

}
