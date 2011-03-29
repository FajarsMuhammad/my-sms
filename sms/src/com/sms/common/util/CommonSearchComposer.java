package com.sms.common.util;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.RowRenderer;

public abstract class CommonSearchComposer extends GenericForwardComposer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Div divSearchResult;
	protected ListModelList modelList;
	protected Grid gridSearchResult;
	
	protected abstract RowRenderer createGridRowRenderer();
	protected abstract void search();
	

}
