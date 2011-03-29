package com.mycrud.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.zkoss.zkplus.hibernate.HibernateUtil;

public abstract class BasisDAOImpl<T, ID extends Serializable> {
	
	//private static Logger log = Logger.getLogger(HibernateSessionConversationFilt.class);
	private Class<T> persistentClass;
	private SessionFactory sf = HibernateUtil.getSessionFactory(); 

	@SuppressWarnings("unchecked")
	public BasisDAOImpl()
	{
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Session getSession()
	{
		return sf.getCurrentSession();
	}
	
	public Class<T> getPersistentClass()
	{
		return persistentClass;
	}

	public void clear()
	{
		getSession().clear();
	}
	
	public void evict(Object obj)
	{
		getSession().evict(obj);
	}

	public void refresh(Object obj)
	{
		getSession().refresh(obj);
	}
	
	@SuppressWarnings("unchecked")
	public T findById(ID id, boolean lock)
	{
		T entity;
		if (lock)
			entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
		else
			entity = (T) getSession().load(getPersistentClass(), id);
		
		return entity;
	}

	@SuppressWarnings("unchecked")
	public T getById(ID id, boolean lock)
	{
		T entity;
		if (lock)
			entity = (T) getSession().get(getPersistentClass(), id, LockMode.UPGRADE);
		else
			entity = (T) getSession().get(getPersistentClass(), id);
		
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String... excludeProperty)
	{
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty)
		{
			example.excludeProperty(exclude);
		}
		crit.add(example);
		
		return crit.list();
	}
	
	public void flush()
	{
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	public T save(T entity)
	{
		getSession().save(entity);
		return entity;
	}

	public void update(T entity)
	{
		getSession().update(entity);
	}

	public void delete(T entity)
	{
		getSession().delete(entity);
	}
	
	public void deleteFlag(T entity)
	{
		try {
			Field deleted = (Field) entity.getClass().getDeclaredField("deleted");
			if (deleted !=null) {
				deleted.set(entity, new Integer(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//log.debug("error ==" + e.toString());
			e.printStackTrace();
		}
		getSession().update(entity);
	} 

}
