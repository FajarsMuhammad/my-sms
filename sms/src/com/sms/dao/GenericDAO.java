package com.sms.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDAO<T, ID extends Serializable>
{
    public T findById(final ID id);

    public void save(final T domain);

    public void save(List<T> domains);

    public void delete(final T domain);

    public Long count();

    public List<T> findAll();

    public List<T> findByExample(T exampleInstance, String... excludeProperty);

}
