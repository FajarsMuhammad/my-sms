package com.sms.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class GenericDAOImpl<T, ID extends Serializable> extends HibernateDaoSupport implements GenericDAO<T, ID>
{
   
    @SuppressWarnings("unchecked")
    public Class domainClass;

    @SuppressWarnings("unchecked")
    public GenericDAOImpl()
    {
        this.domainClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public T findById(ID id)
    {
        //final T domain = (T) getHibernateTemplate().load(domainClass, id);
        final T domain = (T) getHibernateTemplate().get(domainClass, id);
        getHibernateTemplate().initialize(domain);
        return domain;
    }

    public void save(T domain)
    {
        getHibernateTemplate().saveOrUpdate(domain);
        getHibernateTemplate().flush();
    }

    public void save(List<T> domains)
    {
        for (T domain : domains)
        {
            getHibernateTemplate().saveOrUpdate(domain);
            getHibernateTemplate().flush();
        }
    }

    public void delete(T domain)
    {
        getHibernateTemplate().delete(domain);
    }

    @SuppressWarnings("unchecked")
    public Long count()
    {
        List list = getHibernateTemplate().find("select count(*) from " + domainClass.getName() + " x");
        Long count = (Long) list.get(0);
        return count;
    }

    @SuppressWarnings("unchecked")
    public List findAll()
    {
        return getHibernateTemplate().find("from " + domainClass.getName());
    }

    @SuppressWarnings("unchecked")
    public List findByExample(T exampleInstance, String... excludeProperty)
    {
        Criteria crit = getSession().createCriteria(domainClass);
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty)
        {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }


   
}