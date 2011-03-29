package com.sms.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContexHelper implements ApplicationContextAware{
	private static ApplicationContext ctx;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
	}
	
	public static ApplicationContext getContext(){
		return ctx;
	}
	
	public static Object getBean(String name){
		Object o = null;
		try{
			o = getContext().getBean(name);
		}catch (NoSuchBeanDefinitionException e) {
			// TODO: handle exception
			
		}
		return o;
	}

}
