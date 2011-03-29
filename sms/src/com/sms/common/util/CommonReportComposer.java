package com.sms.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public abstract class CommonReportComposer extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LogManager
			.getLogger(CommonReportComposer.class);

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		log.info("do after composer");
		super.doAfterCompose(comp);
	}

	
	public void onClick$btnView(Event e) {
		log.info("view report");
		showReport();
	}

	public void showReport() {
		Map<String, Object> args = new HashMap<String, Object>();

		String reportName = getReportName();

		if (reportName != null) {
			args.put("reportName", reportName);
			args.put("reportParam", getReportParameter());

			log.info("show report : " + getReportName());

			/*Window c = (Window) Executions.createComponents(ResourceHelper
					.getFoundationResource("zul.reportViewer"), null, args);*/

			try {
				//c.doModal();
			} catch (Exception ex) {
				log.info(ex.getMessage(), ex);
			}
		}
	}

	public String getReportName() {
		return null;
	}

	public Map<String, Object> getReportParameter() {
		return null;
	}
}
