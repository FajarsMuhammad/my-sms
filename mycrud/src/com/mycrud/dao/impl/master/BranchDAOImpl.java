package com.mycrud.dao.impl.master;

import java.util.List;

import com.mycrud.bean.master.Branch;
import com.mycrud.dao.BasisDAO;
import com.mycrud.dao.master.BranchDAO;

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
