//package com.sms.common.util;
//
//
//
//import java.io.IOException;
//
//import java.io.InputStream;
//
//import java.util.Properties;
//
//import java.util.logging.Level;
//
//
//
//import javax.servlet.ServletContext;
//
//
//
//import org.eclipse.birt.core.exception.BirtException;
//
//import org.eclipse.birt.core.framework.IPlatformContext;
//
//import org.eclipse.birt.core.framework.Platform;
//
//import org.eclipse.birt.core.framework.PlatformServletContext;
//
//import org.eclipse.birt.report.engine.api.EngineConfig;
//
//import org.eclipse.birt.report.engine.api.IReportEngine;
//
//import org.eclipse.birt.report.engine.api.IReportEngineFactory;
//
///**
//
// * created by: dsousa
//
// */
//
//
//
//public class BirtEngine {
//
//
//
//	private static IReportEngin birtEngine = null;
//
//
//
//	private static Properties configProps = new Properties();
//
//
//
//	private final static String configFile = "BirtConfig.properties";
//
//
//
//	public static synchronized void initBirtConfig() {
//
//		loadEngineProps();
//
//	}
//
//
//
//	public static synchronized IReportEngine getBirtEngine(ServletContext sc) {
//
//		if (birtEngine == null) {
//
//			EngineConfig config = new EngineConfig();
//
//			if (configProps != null) {
//
//				String logLevel = configProps.getProperty("logLevel");
//
//				Level level = Level.OFF;
//
//				if ("SEVERE".equalsIgnoreCase(logLevel)) {
//
//					level = Level.SEVERE;
//
//				} else if ("WARNING".equalsIgnoreCase(logLevel)) {
//
//					level = Level.WARNING;
//
//				} else if ("INFO".equalsIgnoreCase(logLevel)) {
//
//					level = Level.INFO;
//
//				} else if ("CONFIG".equalsIgnoreCase(logLevel)) {
//
//					level = Level.CONFIG;
//
//				} else if ("FINE".equalsIgnoreCase(logLevel)) {
//
//					level = Level.FINE;
//
//				} else if ("FINER".equalsIgnoreCase(logLevel)) {
//
//					level = Level.FINER;
//
//				} else if ("FINEST".equalsIgnoreCase(logLevel)) {
//
//					level = Level.FINEST;
//
//				} else if ("OFF".equalsIgnoreCase(logLevel)) {
//
//					level = Level.OFF;
//
//				}
//
//
//
//				config.setLogConfig(configProps.getProperty("logDirectory"),
//
//						level);
//
//			}
//
//
//
//			//config.setEngineHome("");
//
//			IPlatformContext context = new PlatformServletContext(sc);
//
//			config.setPlatformContext(context);
//
//
//
//			try {
//
//				Platform.startup(config);
//
//			} catch (BirtException e) {
//
//				e.printStackTrace();
//
//			}
//
//
//
//			IReportEngineFactory factory = (IReportEngineFactory) Platform
//
//					.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
//
//			birtEngine = factory.createReportEngine(config);
//
//
//
//		}
//
//		return birtEngine;
//
//	}
//
//
//
//	public static synchronized void destroyBirtEngine() {
//
//		if (birtEngine == null) {
//
//			return;
//
//		}
//
//		birtEngine.destroy();
//
//		Platform.shutdown();
//
//		birtEngine = null;
//
//	}
//
//
//
//	public Object clone() throws CloneNotSupportedException {
//
//		throw new CloneNotSupportedException();
//
//	}
//
//
//
//	private static void loadEngineProps() {
//
//		try {
//
//			// Config File must be in classpath
//
//			ClassLoader cl = Thread.currentThread().getContextClassLoader();
//
//			InputStream in = null;
//
//			in = cl.getResourceAsStream(configFile);
//
//			configProps.load(in);
//
//			in.close();
//
//
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		}
//
//
//
//	}
//
//
//
//}
//

