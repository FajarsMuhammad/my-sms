package com.sms.dao.impl.master;

import java.util.List;

import com.sms.bean.master.Branch;
import com.sms.dao.BasisDAO;
import com.sms.dao.master.BranchDAO;

public class BranchDAOImpl extends BasisDAO<Branch> implements BranchDAO {
	

	@Override
	public void initialize(Branch branch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Branch> getAllBranchs() {
		return getHibernateTemplate().loadAll(Branch.class);
	}

	

}
