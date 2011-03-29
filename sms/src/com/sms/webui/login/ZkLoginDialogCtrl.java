/**
 * Copyright 2010 the original author or authors.
 * 
 * This file is part of Zksample2. http://zksample2.sourceforge.net/
 *
 * Zksample2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Zksample2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Zksample2.  If not, see <http://www.gnu.org/licenses/gpl.html>.
 */
package com.sms.webui.login;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.sms.webui.util.WindowBaseCtrl;

/**
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++<br>
 * This is the controller class for the /WEB-INF/zkloginDialog.zul file.<br>
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++<br>
 * 
 * @author bbruhns
 * @author sgerth
 */
public class ZkLoginDialogCtrl extends WindowBaseCtrl implements Serializable {

	private transient final static Logger logger = Logger.getLogger(ZkLoginDialogCtrl.class);
	private static final long serialVersionUID = -71422545405325060L;

	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * All the components that are defined here and have a corresponding
	 * component with the same 'id' in the zul-file are getting autowired by our
	 * 'extends WindowBaseCtrl'.
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */
	protected Window loginwin; // autowired
	protected Textbox txtbox_Username; // autowired
	protected Textbox txtbox_Password; // autowired
	
	/**
	 * default constructor. <br>
	 */
	public ZkLoginDialogCtrl() {
		super();

		logger.debug("super()");
	}

	public void onCreate$loginwin(Event event) throws Exception {
		logger.debug(event.toString());

		doOnCreateCommon(loginwin); // do the autowire

		// only for testing
		txtbox_Username.setValue("admin");
		txtbox_Password.setValue("admin");

		txtbox_Username.focus(); // set the focus on UserName

		loginwin.setShadow(false);
		loginwin.doModal();

	}


	/**
	 * when the "close" button is clicked. <br>
	 * 
	 * @throws IOException
	 */
	public void onClick$button_ZKLoginDialog_Close() throws IOException {

		Executions.sendRedirect("/j_spring_logout");
	}


	/**
	 * Get the actual date/time on server. <br>
	 * Not used at time.<br>
	 * 
	 * @return String of date/time
	 */
	private String getDateTime() {
		//return ZksampleDateFormat.getDateTimeLongFormater().format(new Date());
		return null;
	}

	

}
