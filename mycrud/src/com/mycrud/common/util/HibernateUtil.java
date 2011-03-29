package com.mycrud.common.util;

import org.apache.log4j.*;
import org.hibernate.*;
import org.hibernate.cfg.*;


public class HibernateUtil
{
	private static Logger log = Logger.getLogger(HibernateUtil.class);
	private static SessionFactory sessionFactory;
	
	static 
	{
		try 
		{
			log.info("Load Hibernate configuration and build Session Factory");
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} 
		catch (Throwable ex) 
		{
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() 
	{
		return sessionFactory;
	}
	
	public static void shutdown() 
	{
		getSessionFactory().close();
	}
	
	
}
