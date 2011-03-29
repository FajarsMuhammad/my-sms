package com.sms.dao;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BasisDAO<T> extends HibernateDaoSupport {
	
	protected BasisDAO() {
	}



	protected T merge(T entity) throws DataAccessException {
		return (T) getHibernateTemplate().merge(entity);
	}

	protected void persist(T entity) throws DataAccessException {
		getHibernateTemplate().persist(entity);
	}

	public void refresh(T entity) throws DataAccessException {
		getHibernateTemplate().refresh(entity);
	}

	public void save(T entity) throws DataAccessException {
		getHibernateTemplate().save(entity);
	}

	public void saveOrUpdate(T entity) throws DataAccessException {
		getHibernateTemplate().saveOrUpdate(entity);
	}


	public void update(T entity) throws DataAccessException {
		getHibernateTemplate().update(entity);
	}

	public void delete(T entity) throws DataAccessException {
		getHibernateTemplate().delete(entity);
	}

	protected void deleteAll(Collection<T> entities) throws DataAccessException {
		getHibernateTemplate().deleteAll(entities);
	}

	protected T get(Class<T> entityClass, Serializable id) throws DataAccessException {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

}
