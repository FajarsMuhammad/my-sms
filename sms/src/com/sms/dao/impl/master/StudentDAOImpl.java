package com.sms.dao.impl.master;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;

import com.sms.bean.master.Student;
import com.sms.dao.BasisDAO;
import com.sms.dao.master.StudentDAO;

public class StudentDAOImpl extends BasisDAO<Student> implements StudentDAO {

	@SuppressWarnings("unchecked")
	public List<Student> getListStudent(List<Object> columList,
			List<Object> valueList, int firstResult, int maxResult) {
		int columnSize = 0;
		int valueSize = 0;

		if (columList != null)
			columnSize = columList.size();
		if (valueList != null)
			valueSize = valueList.size();

		if (columnSize != valueSize)
			throw new HibernateException(
					"Number of 'column' must be equals with number of 'value'");

		DetachedCriteria criteria = DetachedCriteria.forClass(Student.class);
		criteria.add(Restrictions.eq("deleted", new Integer(0)));
		criteria.addOrder(Order.asc("nis"));
		
		//criteria.setProjection(Projections.distinct(Projections.property("id")));  for distinct
		//or
		//criteria.setResultTransformer (Criteria.DISTINCT_ROOT_ENTITY); 
		
		for (int i = 0; i < columnSize; i++)
			criteria.add(Restrictions.ilike((String) columList.get(i), "%"
					+ (String) valueList.get(i) + "%"));

		return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResult);
		
	}

	public int getCountAllStudent() {
		return DataAccessUtils.intResult(getHibernateTemplate().find(
				"SELECT count(*) FROM Student WHERE deleted= 0"));
	}

	@SuppressWarnings("unchecked")
	public Long getCountStudentByCode(String nis) {
		List list = getHibernateTemplate().find(
				"SELECT count(*) FROM Student WHERE nis='" + nis +"' AND deleted = 0");
		Long count = (Long) list.get(0);
		return count;

	}

	public Student getStudentById(long id) {
		return get(Student.class, Long.valueOf(id));
	}

	public void save(Student student) throws DataAccessException {
		super.save(student);
	}

	public void saveOrUpdate(Student student) throws DataAccessException {
		super.saveOrUpdate(student);
	}

	public void delete(Student student) throws DataAccessException {
		super.delete(student);
	}

}
