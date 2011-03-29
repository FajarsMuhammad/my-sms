package com.sms.service.impl.master;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTransactionManager;

import com.sms.bean.master.Branch;
import com.sms.dao.master.BranchDAO;
import com.sms.service.master.BranchService;

public class BranchServiceImpl implements BranchService {
	
	private HibernateTransactionManager transactionManager;
	
	public HibernateTransactionManager getTransactionManager() {
		return transactionManager;
	}
	
	public BranchServiceImpl(){
		
	}

	public void setTransactionManager(HibernateTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	private BranchDAO branchDAO;

	public BranchDAO getBranchDAO() {
		return branchDAO;
	}

	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}

	@Override
	public List<Branch> getAllBranchs() {
		return getBranchDAO().getAllBranchs();
	}

	@Override
	public void delete(Branch branch) {
		getBranchDAO().delete(branch);
		
	}

	@Override
	public void saveOrUpdate(Branch branch) {
		getBranchDAO().saveOrUpdate(branch);
	}

	@Override
	public void save(Branch branch) {
		getBranchDAO().save(branch);
	}

}
