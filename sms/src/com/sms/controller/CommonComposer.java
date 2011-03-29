package com.sms.controller;

import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public abstract class CommonComposer extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3587039907014480894L;

	//private static final Logger log = LogManager
	//		.getLogger(CommonComposer.class);

	public void navigateTo(String url, Map<String, Object> arg, Component from) {
		Component parent = from.getParent();
		Executions.createComponents(url, parent, arg);
		from.detach();
	}

	public void backTo(Component to, Component from) {
		to.setParent(from.getParent());
		from.detach();
	}

}
