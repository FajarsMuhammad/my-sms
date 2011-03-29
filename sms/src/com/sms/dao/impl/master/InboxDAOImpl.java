package com.sms.dao.impl.master;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.sms.bean.master.Inbox;
import com.sms.dao.BasisDAO;
import com.sms.dao.master.InboxDAO;

public class InboxDAOImpl extends BasisDAO<Inbox> implements InboxDAO {


	@SuppressWarnings("unchecked")
	public List<Inbox> getListInbox() {		
		DetachedCriteria criteria = DetachedCriteria.forClass(Inbox.class);
		criteria.add(Restrictions.eq("deleted", new Integer(0)));
		
		return getHibernateTemplate().findByCriteria(criteria);
		
	}

}
